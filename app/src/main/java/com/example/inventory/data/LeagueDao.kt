package com.example.inventory.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow
import org.setu.model.League

@Dao
interface LeagueDao {

        @Insert(onConflict = OnConflictStrategy.IGNORE)
        suspend fun insert(item: League)

        @Update
        suspend fun update(item: League)

        @Delete
        suspend fun delete(item: League)

        @Query("SELECT * from leagues WHERE uid = :id")
        fun getLeague(id: Int): Flow<League?>

        @Query("SELECT * from leagues ORDER BY name ASC")
        fun getAllLeagues(): Flow<List<League>>

}