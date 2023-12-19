package org.setu.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "leagues")
class League (
    var name: String,
    var country: String,
    var type: String,
    var crestUrl: String,
    var countryFlagUrl: String,
    @PrimaryKey(autoGenerate = true)
    val uid: Int = 0
) {

    init{
        //Validation here
        require(name.isNotBlank()){"Name cannot be blank"}
        require(country.isNotBlank()){"Country cannot be blank"}
        require(crestUrl.isNotBlank()){"CrestUrl cannot be blank"}
    }

    override fun toString(): String {
        return "League: $name, $country"
    }
}
