package org.setu.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.league.model.Item

@Entity(tableName = "leagues")
class League (
    var name: String,
    var country: String,
    var type: String,
    var crestUrl: String,
    var countryFlagUrl: String,
    @PrimaryKey(autoGenerate = true)
    val uid: Int = 0
) : Item(){

    //Secondary constructor
    constructor(): this("","","","","")


    override fun toString(): String {
        return "League: $name, $country"
    }
}
