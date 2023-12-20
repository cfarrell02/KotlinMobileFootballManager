package com.example.league.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow
import org.setu.model.Player

@Dao
interface PlayerDao {

    @Insert
    suspend fun insertPlayer(player: Player)

    @Delete
    suspend fun deletePlayer(player: Player)

    @Update
    suspend fun updatePlayer(player: Player)

    @Query("SELECT * FROM players WHERE uid = :id")
    fun getPlayer(id: Int): Flow<Player?>

    @Query("SELECT * FROM players WHERE teamId = :teamId")
    fun getPlayersByTeam(teamId: Int): Flow<List<Player>>

}