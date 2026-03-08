package com.predictora.data.repository

import com.predictora.data.api.AuthApiService
import com.predictora.data.models.LoginRequest
import com.predictora.data.models.RegisterRequest
import com.predictora.data.models.TokenResponse
import com.predictora.data.local.TokenDataStore
import com.predictora.utils.Result
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AuthRepository @Inject constructor(
    private val apiService: AuthApiService,
    private val tokenDataStore: TokenDataStore
) {

    val isLoggedIn: Flow<Boolean> = tokenDataStore.accessTokenFlow.let { flow ->
        kotlinx.coroutines.flow.map(flow) { token -> !token.isNullOrBlank() }
    }

    suspend fun login(email: String, password: String): Result<TokenResponse> {
        return try {
            val response = apiService.login(LoginRequest(email, password))
            if (response.isSuccessful) {
                val body = response.body()!!
                tokenDataStore.saveTokens(body.accessToken, body.refreshToken)
                Result.Success(body)
            } else {
                val errorMsg = when (response.code()) {
                    401 -> "Invalid email or password"
                    403 -> "Account suspended. Contact support."
                    429 -> "Too many attempts. Try again later."
                    else -> "Login failed. Please try again."
                }
                Result.Error(errorMsg)
            }
        } catch (e: Exception) {
            Result.Error("Network error: ${e.localizedMessage ?: "Check your connection"}")
        }
    }

    suspend fun register(
        name: String,
        email: String,
        password: String
    ): Result<TokenResponse> {
        return try {
            val response = apiService.register(RegisterRequest(name, email, password))
            if (response.isSuccessful) {
                val body = response.body()!!
                tokenDataStore.saveTokens(body.accessToken, body.refreshToken)
                Result.Success(body)
            } else {
                val errorMsg = when (response.code()) {
                    409 -> "An account with this email already exists"
                    else -> "Registration failed. Please try again."
                }
                Result.Error(errorMsg)
            }
        } catch (e: Exception) {
            Result.Error("Network error: ${e.localizedMessage ?: "Check your connection"}")
        }
    }

    suspend fun loginWithBiometrics(): Result<TokenResponse> {
        // Retrieve stored credentials using biometric prompt
        return try {
            val storedToken = tokenDataStore.getBiometricToken()
            if (storedToken != null) {
                // Validate stored token is still valid
                val response = apiService.refreshToken(storedToken)
                if (response.isSuccessful) {
                    val body = response.body()!!
                    tokenDataStore.saveTokens(body.accessToken, body.refreshToken)
                    Result.Success(body)
                } else {
                    Result.Error("Biometric session expired. Please sign in manually.")
                }
            } else {
                Result.Error("No biometric credentials stored. Please sign in manually first.")
            }
        } catch (e: Exception) {
            Result.Error("Biometric authentication failed: ${e.localizedMessage}")
        }
    }

    suspend fun refreshToken(): Result<TokenResponse> {
        return try {
            val refreshToken = tokenDataStore.getRefreshToken()
                ?: return Result.Error("Session expired. Please log in again.")
            val response = apiService.refreshToken(refreshToken)
            if (response.isSuccessful) {
                val body = response.body()!!
                tokenDataStore.saveTokens(body.accessToken, body.refreshToken)
                Result.Success(body)
            } else {
                tokenDataStore.clearTokens()
                Result.Error("Session expired. Please log in again.")
            }
        } catch (e: Exception) {
            Result.Error("Network error: ${e.localizedMessage}")
        }
    }

    suspend fun logout() {
        try {
            apiService.logout()
        } catch (e: Exception) {
            // Ignore network errors on logout
        } finally {
            tokenDataStore.clearTokens()
        }
    }
}
