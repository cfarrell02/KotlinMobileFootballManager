package com.example.inventory.data

import kotlinx.coroutines.flow.Flow
import org.setu.model.League

class OfflineLeagueRepository (private val leagueDao: LeagueDao) : LeagueRepository {
    override fun getAllLeaguesStream(): Flow<List<League>> = leagueDao.getAllLeagues()

    override fun getLeagueStream(id: Int): Flow<League?> = leagueDao.getLeague(id)

    override suspend fun insertLeague(league: League) = leagueDao.insert(league)

    override suspend fun deleteLeague(league: League) = leagueDao.delete(league)

    override suspend fun updateLeague(league: League) = leagueDao.update(league)
}