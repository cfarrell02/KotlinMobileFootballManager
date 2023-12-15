package com.example.FootballApp.data

import androidx.room.Room
import android.content.Context
import androidx.room.*
import androidx.room.RoomDatabase
import org.setu.model.League

@Database(entities = [League::class], version = 1, exportSchema = false)
abstract class LeagueDatabase: RoomDatabase() {
    abstract fun leagueDao(): leagueDao

    companion object {
        @Volatile
        private var INSTANCE: LeagueDatabase? = null

        fun getDataBase(context: Context): LeagueDatabase {
            return INSTANCE ?: synchronized(this) {
                Room.databaseBuilder(context, LeagueDatabase::class.java, "league_database")
                    .build()
                    .also { INSTANCE = it }
            }
        }
    }
}