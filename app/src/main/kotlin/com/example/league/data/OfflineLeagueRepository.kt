package com.example.league.data

import com.example.league.network.LeagueAPIService
import kotlinx.coroutines.flow.Flow
import org.setu.model.League

class OfflineLeagueRepository (private val leagueDao: LeagueDao, private val leagueAPIService: LeagueAPIService) : LeagueRepository {
    override fun getAllLeaguesStream(): Flow<List<League>> = leagueDao.getAllLeagues()

    override fun getLeagueStream(id: Int): Flow<League?> = leagueDao.getLeague(id)

    override suspend fun insertLeague(league: League) = leagueDao.insert(league)

    override suspend fun deleteLeague(league: League) = leagueDao.delete(league)

    override suspend fun updateLeague(league: League) = leagueDao.update(league)

    override suspend fun searchLeague(query: String): List<League>{
        if (query.length < 3) {
            return emptyList()
        }
        val leagues = leagueAPIService.getLeagues(leagueName = query).response
        if (leagues != null) {
            return leagues.map { leagueResponse ->
                League(
                    name = leagueResponse.league?.name ?: "",
                    country = leagueResponse.country?.name ?: "",
                    type = leagueResponse.league?.type ?: "",
                    countryFlagUrl = leagueResponse.country?.flag?: "",
                    crestUrl = leagueResponse.league?.logo ?: "",
                    uid = leagueResponse.league?.id ?: 0
                )
            }
        }
        return emptyList()
    }
}