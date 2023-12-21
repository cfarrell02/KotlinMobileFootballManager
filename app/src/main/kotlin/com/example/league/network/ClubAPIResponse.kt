package com.example.league.network

import kotlinx.serialization.Serializable

//This API response class was generated using https://app.quicktype.io/


@Serializable
data class ClubAPIResponse(
    val get: String?,
    val parameters: Parameters?,
    val errors: List<String>?,
    val results: Int?,
    val paging: Paging?,
    val response: List<TeamResponse>?
)

@Serializable
data class TeamResponse(
    val team: Team?,
    val venue: Venue?
)

@Serializable
data class Team(
    val id: Int?,
    val name: String?,
    val code: String?,
    val country: String?,
    val founded: Int?,
    val national: Boolean?,
    val logo: String?
)

@Serializable
data class Venue(
    val id: Int?,
    val name: String?,
    val address: String?,
    val city: String?,
    val capacity: Int?,
    val surface: String?,
    val image: String?
)
