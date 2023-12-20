package com.example.inventory.network

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class LeagueApiResponse(
    val get: String?,
    val parameters: Parameters?,
    val errors: List<String>?,
    val results: Int?,
    val paging: Paging?,
    val response: List<Response>?
)

@Serializable
data class Parameters(
    val search: String?
)

@Serializable
data class Paging(
    val current: Int?,
    val total: Int?
)

@Serializable
data class Response(
    val league: League?,
    val country: Country?,
    val seasons: List<Season>?
)

@Serializable
data class League(
    val id: Int?,
    val name: String?,
    val type: String?,
    val logo: String?
)

@Serializable
data class Country(
    val name: String?,
    val code: String?,
    val flag: String?
)

@Serializable
data class Season(
    val year: Int?,
    val start: String?,
    val end: String?,
    val current: Boolean?,
    val coverage: Coverage?
)

@Serializable
data class Coverage(
    val fixtures: Fixtures?,
    val standings: Boolean?,
    val players: Boolean?,
    @SerialName("top_scorers")
    val topScorers: Boolean?,
    @SerialName("top_assists")
    val topAssists: Boolean?,
    @SerialName("top_cards")
    val topCards: Boolean?,
    val injuries: Boolean?,
    val predictions: Boolean?,
    val odds: Boolean?
)

@Serializable
data class Fixtures(
    val events: Boolean?,
    val lineups: Boolean?,
    @SerialName("statistics_fixtures")
    val statisticsFixtures: Boolean?,
    @SerialName("statistics_players")
    val statisticsPlayers: Boolean?
)
