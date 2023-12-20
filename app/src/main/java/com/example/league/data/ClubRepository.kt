package com.example.league.data

import com.example.league.network.LeagueAPIService
import kotlinx.coroutines.flow.Flow
import org.setu.model.Club

class ClubRepository (private val clubDao: ClubDao, private val leagueAPIService: LeagueAPIService)  {

    fun getAllClubsStream(): Flow<List<Club>> = clubDao.getAllClubs()

    fun getClubStream(id: Int): Flow<Club?> = clubDao.getClub(id)

    suspend fun insertClub(club: Club) = clubDao.insert(club)

    suspend fun deleteClub(club: Club) = clubDao.delete(club)

    suspend fun deleteClubsByLeagueId(leagueId: Int) = clubDao.deleteClubsByLeagueId(leagueId)

    suspend fun updateClub(club: Club) = clubDao.update(club)

    fun getClubsByLeagueId(leagueId: Int): Flow<List<Club>> = clubDao.getClubsByLeagueId(leagueId)

    suspend fun searchClub(query: String): List<Club>{
        val clubs = leagueAPIService.getTeams(teamName = query).response
        if (clubs != null) {
            return clubs.map { clubResponse ->
                Club(
                    name = clubResponse.team?.name ?: "",
                    code = clubResponse.team?.code ?: "",
                    country = clubResponse.team?.country ?: "",
                    founded = clubResponse.team?.founded ?: 0,
                    national = clubResponse.team?.national ?: false,
                    crestUrl = clubResponse.team?.logo ?: "",
                    venue = clubResponse.venue?.name ?: "",
                    venueAddress = clubResponse.venue?.address ?: "",
                    venueCity = clubResponse.venue?.city ?: "",
                    venueCapacity = clubResponse.venue?.capacity ?: 0,
                    venueSurface = clubResponse.venue?.surface ?: "",
                    venueImageUrl = clubResponse.venue?.image ?: "",
                    leagueId = 0,
                    uid = clubResponse.team?.id ?: 0
                )

            }
        }
        return emptyList()
    }
}