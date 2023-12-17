package com.example.FootballApp.data

import org.setu.model.League
import kotlinx.coroutines.flow.Flow

interface LeagueRepository {

    suspend fun addLeague(league: League)

    suspend fun updateLeague(league: League)

    fun getLeagues(): Flow<List<League>>

    fun getLeagueById(id: String): Flow<League>

    suspend fun deleteLeague(league: League)


}