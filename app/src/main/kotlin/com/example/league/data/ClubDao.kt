package com.example.league.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow
import org.setu.model.Club

@Dao
interface ClubDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(item: Club)

    @Update
    suspend fun update(item: Club)

    @Delete
    suspend fun delete(item: Club)

    @Query("SELECT * from clubs WHERE uid = :id")
    fun getClub(id: Int): Flow<Club?>

    @Query("SELECT * from clubs ORDER BY name ASC")
    fun getAllClubs(): Flow<List<Club>>

    @Query("SELECT * from clubs WHERE leagueId = :leagueId ORDER BY name ASC")
    fun getClubsByLeagueId(leagueId: Int): Flow<List<Club>>

    @Query("DELETE FROM clubs WHERE leagueId = :leagueId")
    suspend fun deleteClubsByLeagueId(leagueId: Int)

}