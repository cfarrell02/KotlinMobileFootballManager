package com.example.FootballApp.data

import kotlinx.coroutines.flow.Flow
import org.setu.model.League

class PersistentLeagueRepository(private val leagueDao: leagueDao) : LeagueRepository {
    override suspend fun addLeague(league: League) {
        leagueDao.insert(league)
    }

    override suspend fun updateLeague(league: League) {
        leagueDao.update(league)
    }

    override fun getLeagues(): Flow<List<League>> {
        return leagueDao.getAllLeagues()
    }

    override fun getLeagueById(id: String): Flow<League> {
        return leagueDao.getLeagueById(id)
    }

    override suspend fun deleteLeague(league: League) {
        leagueDao.delete(league)
    }
}