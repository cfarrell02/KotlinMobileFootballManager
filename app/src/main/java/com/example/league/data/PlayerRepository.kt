package com.example.league.data

import com.example.league.network.LeagueAPIService
import org.setu.model.Player

class PlayerRepository (private val playerDao: PlayerDao, private val leagueAPIService: LeagueAPIService) {

    fun getAllPlayersByTeamStream(teamId: Int) = playerDao.getPlayersByTeam(teamId)

    fun getPlayerStream(id: Int) = playerDao.getPlayer(id)

    suspend fun insertPlayer(player: Player) = playerDao.insertPlayer(player)

    suspend fun deletePlayer(player: Player) = playerDao.deletePlayer(player)

    suspend fun updatePlayer(player: Player) = playerDao.updatePlayer(player)

    suspend fun searchPlayer(query: String, leagueId:Int):List<Player>{
        val response = leagueAPIService.getPlayers(search = query, leagueId = leagueId)
        val players = response.response
        if (players != null) {
            return players.map {
                Player(
                    name = it.player?.name ?: "",
                    countryOfBirth = it.player?.birth?.country ?: "",
                    dateOfBirth = it.player?.birth?.date ?: "",
                    placeOfBirth = it.player?.birth?.place ?: "",
                    firstName = it.player?.name ?: "",
                    lastName = it.player?.name ?: "",
                    nationality = it.player?.nationality ?: "",
                    teamId = 0,
                    leagueId = leagueId,
                    pictureUrl = it.player?.photo ?: "",
                    position = it.statistics?.get(0)?.games?.position ?: "",
                    number = it.statistics?.get(0)?.games?.number ?: -1
                )
            }
        }
        return emptyList()
    }
}