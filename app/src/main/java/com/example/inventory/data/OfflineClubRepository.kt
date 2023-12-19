package com.example.inventory.data

import kotlinx.coroutines.flow.Flow
import org.setu.model.Club

class OfflineClubRepository (private val clubDao: ClubDao)  {

    fun getAllLeaguesStream(): Flow<List<Club>> = clubDao.getAllClubs()

    fun getLeagueStream(id: Int): Flow<Club?> = clubDao.getClub(id)

    suspend fun insertLeague(club: Club) = clubDao.insert(club)

    suspend fun deleteLeague(club: Club) = clubDao.delete(club)

    suspend fun updateLeague(club: Club) = clubDao.update(club)

    fun getClubsByLeagueId(leagueId: Int): Flow<List<Club>> = clubDao.getClubsByLeagueId(leagueId)
}