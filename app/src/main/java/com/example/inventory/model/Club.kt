package org.setu.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "clubs")
class Club (
    var name: String,
    var city: String,
    var stadium: String,
    var founded: String,
    var crestUrl: String,
    val leagueId : Int,
    @PrimaryKey(autoGenerate = true)
    val uid: Int

) {



    init{
        //Validation here
        require(name.isNotBlank()){"Name cannot be blank"}
        require(city.isNotBlank()){"City cannot be blank"}
        require(stadium.isNotBlank()){"Stadium cannot be blank"}
    }



    override fun toString(): String {
        return "Club: $name, $city, $stadium"
    }

}
