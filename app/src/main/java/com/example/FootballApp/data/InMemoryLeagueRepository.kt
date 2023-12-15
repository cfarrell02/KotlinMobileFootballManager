package com.example.FootballApp.data

import kotlinx.coroutines.flow.Flow
import org.setu.model.League

class InMemoryLeagueRepository: LeagueRepository {
    private var leagues = mutableListOf<League>()
    override suspend fun addLeague(league: League) {
        leagues.add(league)
    }

    override fun getLeagueById(id: Int): Flow<League> {
        TODO("Not yet implemented")
    }

    override suspend fun deleteLeague(league: League) {
        TODO("Not yet implemented")
    }

    override suspend fun updateLeague(league: League) {
        val index = leagues.indexOfFirst { it.uid == league.uid }
        if (index != -1) {
            leagues[index] = league
        }
    }

    override fun getLeagues(): Flow<List<League>> {
        TODO()
    }

}