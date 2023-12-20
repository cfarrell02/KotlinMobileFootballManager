package com.example.league.data


import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import org.setu.model.Club
import org.setu.model.League
import org.setu.model.Player

/**
 * Database class with a singleton Instance object.
 */
@Database(entities = [League::class, Club::class, Player::class], version =1, exportSchema = false)
abstract class LeagueDatabase : RoomDatabase() {
    abstract fun leagueDao(): LeagueDao

    abstract fun clubDao(): ClubDao

    abstract fun playerDao(): PlayerDao

    companion object {
        @Volatile
        private var Instance: LeagueDatabase? = null

        fun getDatabase(context: Context): LeagueDatabase {
            // if the Instance is not null, return it, otherwise create a new database instance.
            return Instance ?: synchronized(this) {
                Room.databaseBuilder(context, LeagueDatabase::class.java, "league_database")
                    .build()
                    .also { Instance = it }
            }
        }
    }
}