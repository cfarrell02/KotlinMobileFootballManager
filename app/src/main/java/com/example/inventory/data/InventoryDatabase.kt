package com.example.inventory.data


import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import org.setu.model.Club
import org.setu.model.League

/**
 * Database class with a singleton Instance object.
 */
@Database(entities = [League::class, Club::class], version =1, exportSchema = false)
abstract class InventoryDatabase : RoomDatabase() {
    abstract fun leagueDao(): LeagueDao

    abstract fun clubDao(): ClubDao

    companion object {
        @Volatile
        private var Instance: InventoryDatabase? = null

        fun getDatabase(context: Context): InventoryDatabase {
            // if the Instance is not null, return it, otherwise create a new database instance.
            return Instance ?: synchronized(this) {
                Room.databaseBuilder(context, InventoryDatabase::class.java, "leaguesDatabase")
                    .build()
                    .also { Instance = it }
            }
        }
    }
}