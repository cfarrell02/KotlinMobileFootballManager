package com.example.league.network

import kotlinx.serialization.Serializable

@Serializable
data class PlayerAPIResponse(
    val get: String?,
    val parameters: Parameters2?,
    val errors: List<String>?,
    val results: Int?,
    val paging: Paging2?,
    val response: List<PlayerResponse>?
)

@Serializable
data class Parameters2(
    val league: String?,
    val search: String?
)

@Serializable
data class Paging2(
    val current: Int?,
    val total: Int?
)

@Serializable
data class PlayerResponse(
    val player: Player?,
    val statistics: List<PlayerStatistics>?
)

@Serializable
data class Player(
    val id: Int?,
    val name: String?,
    val firstname: String?,
    val lastname: String?,
    val age: Int?,
    val birth: Birth?,
    val nationality: String?,
    val height: String?,
    val weight: String?,
    val injured: Boolean?,
    val photo: String?
)

@Serializable
data class Birth(
    val date: String?,
    val place: String?,
    val country: String?
)

@Serializable
data class PlayerStatistics(
    val team: Team2?,
    val league: League2?,
    val games: Games?,
    val substitutes: Substitutes?,
    val shots: Shots?,
    val goals: Goals?,
    val passes: Passes?,
    val tackles: Tackles?,
    val duels: Duels?,
    val dribbles: Dribbles?,
    val fouls: Fouls?,
    val cards: Cards?,
    val penalty: Penalty?
)

@Serializable
data class Team2(
    val id: Int?,
    val name: String?,
    val logo: String?
)

@Serializable
data class League2(
    val id: Int?,
    val name: String?,
    val country: String?,
    val logo: String?,
    val flag: String?,
    val season: Int?
)

@Serializable
data class Games(
    val appearences: Int?,
    val lineups: Int?,
    val minutes: Int?,
    val number: Int?,
    val position: String?,
    val rating: String?,
    val captain: Boolean?
)

@Serializable
data class Substitutes(
    val `in`: Int?,
    val out: Int?,
    val bench: Int?
)

@Serializable
data class Shots(
    val total: Int?,
    val on: Int?
)

@Serializable
data class Goals(
    val total: Int?,
    val conceded: Int?,
    val assists: Int?,
    val saves: Int?
)

@Serializable
data class Passes(
    val total: Int?,
    val key: Int?,
    val accuracy: Int?
)

@Serializable
data class Tackles(
    val total: Int?,
    val blocks: Int?,
    val interceptions: Int?
)

@Serializable
data class Duels(
    val total: Int?,
    val won: Int?
)

@Serializable
data class Dribbles(
    val attempts: Int?,
    val success: Int?,
    val past: Int?
)

@Serializable
data class Fouls(
    val drawn: Int?,
    val committed: Int?
)

@Serializable
data class Cards(
    val yellow: Int?,
    val yellowred: Int?,
    val red: Int?
)

@Serializable
data class Penalty(
    val won: Int?,
    val commited: Int?,
    val scored: Int?,
    val missed: Int?,
    val saved: Int?
)
