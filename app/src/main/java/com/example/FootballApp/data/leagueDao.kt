package com.example.FootballApp.data

import androidx.room.*
import org.setu.model.League
import kotlinx.coroutines.flow.Flow
@Dao
interface leagueDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(league: League)

    @Update
    suspend fun update(league: League)

    @Delete
    suspend fun delete(league: League)

    @Query("SELECT * FROM league_table ORDER BY uid ASC")
    fun getAllLeagues(): Flow<List<League>>

    @Query("SELECT * FROM league_table WHERE uid = :id")
    fun getLeagueById(id: Int): Flow<League>


}