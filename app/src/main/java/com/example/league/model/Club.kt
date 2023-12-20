package org.setu.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "clubs")
class Club (
    var name: String,
    var code: String,
    var country: String,
    var national: Boolean,
    var founded: Int,
    var crestUrl: String,
    var venue: String,
    var venueAddress : String,
    var venueCity : String,
    var venueCapacity : Int,
    var venueSurface : String,
    var venueImageUrl: String,
    var leagueId : Int,
    @PrimaryKey(autoGenerate = true)
    val uid: Int

) {
//Secondary constructor
constructor() : this(
        name = "",
        code = "",
        country = "",
        national = false,
        founded = 0,
        crestUrl = "",
        venue = "",
        venueAddress = "",
        venueCity = "",
        venueCapacity = 0,
        venueSurface = "",
        venueImageUrl = "",
        leagueId = 0,
        uid = 0
    )

}
