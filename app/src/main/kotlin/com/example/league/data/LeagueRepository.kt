package com.example.league.data

import kotlinx.coroutines.flow.Flow
import org.setu.model.League

interface LeagueRepository {

    fun getAllLeaguesStream(): Flow<List<League>>

    fun getLeagueStream(id: Int): Flow<League?>

    suspend fun insertLeague(league: League)

    suspend fun deleteLeague(league: League)


    suspend fun updateLeague(league: League)

    suspend fun searchLeague(query: String): List<League>


}