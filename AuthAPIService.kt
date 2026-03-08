package com.predictora.data.api

import com.predictora.data.models.LoginRequest
import com.predictora.data.models.RegisterRequest
import com.predictora.data.models.TokenResponse
import com.predictora.data.models.UserProfile
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST

interface AuthApiService {

    @POST("auth/login")
    suspend fun login(@Body request: LoginRequest): Response<TokenResponse>

    @POST("auth/register")
    suspend fun register(@Body request: RegisterRequest): Response<TokenResponse>

    @POST("auth/refresh")
    suspend fun refreshToken(@Header("x-refresh-token") refreshToken: String): Response<TokenResponse>

    @POST("auth/logout")
    suspend fun logout(): Response<Unit>

    @GET("user/profile")
    suspend fun getProfile(): Response<UserProfile>
}
