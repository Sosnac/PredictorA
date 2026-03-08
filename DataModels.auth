package com.predictora.data.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class LoginRequest(
    val email: String,
    val password: String
)

@Serializable
data class RegisterRequest(
    val name: String,
    val email: String,
    val password: String
)

@Serializable
data class TokenResponse(
    @SerialName("access_token") val accessToken: String,
    @SerialName("refresh_token") val refreshToken: String,
    @SerialName("expires_in") val expiresIn: Long,
    val user: UserProfile
)

@Serializable
data class UserProfile(
    val id: String,
    val name: String,
    val email: String,
    val avatar: String? = null,
    @SerialName("favourite_team") val favouriteTeam: String? = null,
    @SerialName("created_at") val createdAt: String
)

@Serializable
data class Match(
    val id: String,
    @SerialName("home_team") val homeTeam: Team,
    @SerialName("away_team") val awayTeam: Team,
    val status: MatchStatus,
    val score: Score?,
    val league: League,
    @SerialName("start_time") val startTime: String,
    val prediction: Prediction? = null
)

@Serializable
data class Team(
    val id: String,
    val name: String,
    @SerialName("short_name") val shortName: String,
    val crest: String
)

@Serializable
enum class MatchStatus {
    @SerialName("scheduled") SCHEDULED,
    @SerialName("live") LIVE,
    @SerialName("finished") FINISHED,
    @SerialName("postponed") POSTPONED
}

@Serializable
data class Score(
    @SerialName("home") val home: Int,
    @SerialName("away") val away: Int,
    @SerialName("half_time_home") val halfTimeHome: Int? = null,
    @SerialName("half_time_away") val halfTimeAway: Int? = null
)

@Serializable
data class League(
    val id: String,
    val name: String,
    val country: String,
    val logo: String
)

@Serializable
data class Prediction(
    @SerialName("home_win_prob") val homeWinProb: Float,
    @SerialName("draw_prob") val drawProb: Float,
    @SerialName("away_win_prob") val awayWinProb: Float,
    val confidence: Float,
    val tips: List<String>
)

@Serializable
data class MatchAnalysis(
    @SerialName("match_id") val matchId: String,
    @SerialName("home_xg") val homeXg: Float,
    @SerialName("away_xg") val awayXg: Float,
    @SerialName("home_possession") val homePossession: Float,
    @SerialName("away_possession") val awayPossession: Float,
    @SerialName("home_shots") val homeShots: Int,
    @SerialName("away_shots") val awayShots: Int,
    @SerialName("home_shots_on_target") val homeShotsOnTarget: Int,
    @SerialName("away_shots_on_target") val awayShotsOnTarget: Int,
    @SerialName("home_passes") val homePasses: Int,
    @SerialName("away_passes") val awayPasses: Int
)
