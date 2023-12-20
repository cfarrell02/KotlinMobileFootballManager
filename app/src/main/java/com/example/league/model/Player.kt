package org.setu.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "players")
class Player(
    var name: String,
    var firstName: String,
    var lastName: String,
    var dateOfBirth: String,
    var placeOfBirth: String,
    var countryOfBirth: String,
    val position : String,
    var nationality: String,
    var number: Int,
    var pictureUrl: String,
    var teamId: Int,
    var leagueId: Int,
    @PrimaryKey(autoGenerate = true)
    var uid : Int = 0
)  {



}