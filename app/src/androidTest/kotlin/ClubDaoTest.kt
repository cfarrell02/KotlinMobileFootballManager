import android.content.Context
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.league.data.ClubDao
import com.example.league.data.LeagueDatabase
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.setu.model.Club
import java.io.IOException

@RunWith(AndroidJUnit4::class)

class ClubDaoTest {
    private lateinit var clubDao: ClubDao
    private lateinit var db: LeagueDatabase

    @Before
    fun createDb() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        db = Room.inMemoryDatabaseBuilder(context, LeagueDatabase::class.java).allowMainThreadQueries().build()
        clubDao = db.clubDao()
    }

    @After
    @Throws(IOException::class)
    fun closeDb() {
        db.close()
    }

    @Test
    @Throws(Exception::class)
    fun insertAndGetClub() = runBlocking {
        val club = Club(
            name = "Arsenal FC",
            country = "England",
            code = "ARS",
            founded = 1886,
            national = false,
            venue = "Emirates Stadium",
            venueCapacity = 60355,
            venueCity = "London",
            venueAddress = "Hornsey Rd, London N7 7AJ, UK",
            venueSurface = "grass",
            venueImageUrl = "https://media.api-football.com/teams/42.png",
            leagueId = 2,
            crestUrl = "https://media.api-football.com/teams/42.png",
            uid = 1
        )
        clubDao.insert(club)
        val clubItem: Club? = clubDao.getClub(1).first()
        Assert.assertEquals(clubItem?.name, "Arsenal FC")
    }

    @Test
    @Throws(Exception::class)
    fun deleteClub()  = runBlocking{
        val club = Club(
            name = "Arsenal FC",
            country = "England",
            code = "ARS",
            founded = 1886,
            national = false,
            venue = "Emirates Stadium",
            venueCapacity = 60355,
            venueCity = "London",
            venueAddress = "Hornsey Rd, London N7 7AJ, UK",
            venueSurface = "grass",
            venueImageUrl = "https://media.api-football.com/teams/42.png",
            leagueId = 2,
            crestUrl = "https://media.api-football.com/teams/42.png",
            uid = 1
        )
        clubDao.insert(club)
        clubDao.delete(club)
        val clubItem: Club? = clubDao.getClub(1).first()
        Assert.assertEquals(clubItem, null)
    }

    @Test
    @Throws(Exception::class)
    fun updateClub() = runBlocking {
        val club = Club(
            name = "Arsenal FC",
            country = "England",
            code = "ARS",
            founded = 1886,
            national = false,
            venue = "Emirates Stadium",
            venueCapacity = 60355,
            venueCity = "London",
            venueAddress = "Hornsey Rd, London N7 7AJ, UK",
            venueSurface = "grass",
            venueImageUrl = "https://media.api-football.com/teams/42.png",
            leagueId = 2,
            crestUrl = "https://media.api-football.com/teams/42.png",
            uid = 1
        )
        clubDao.insert(club)
        clubDao.update(
            Club(
                name = "Manchester United FC",
                country = "England",
                code = "MUN",
                founded = 1878,
                national = false,
                venue = "Old Trafford",
                venueCapacity = 76212,
                venueCity = "Manchester",
                venueAddress = "Sir Matt Busby Way, Stretford, Manchester M16 0RA, UK",
                venueSurface = "grass",
                venueImageUrl = "https://media.api-football.com/teams/33.png",
                leagueId = 2,
                crestUrl = "https://media.api-football.com/teams/33.png",
                uid = 1
            )
        )
        val clubItem: Club? = clubDao.getClub(1).first()
        Assert.assertEquals(clubItem?.name, "Manchester United FC")
    }
    @Test
    @Throws(Exception::class)
    fun getByLeagueId() = runBlocking {
        val club = Club(
            name = "Arsenal FC",
            country = "England",
            code = "ARS",
            founded = 1886,
            national = false,
            venue = "Emirates Stadium",
            venueCapacity = 60355,
            venueCity = "London",
            venueAddress = "Hornsey Rd, London N7 7AJ, UK",
            venueSurface = "grass",
            venueImageUrl = "https://media.api-football.com/teams/42.png",
            leagueId = 2,
            crestUrl = "https://media.api-football.com/teams/42.png",
            uid = 1
        )
        clubDao.insert(club)
        val club2 = Club(
            name = "Manchester United FC",
            country = "England",
            code = "MUN",
            founded = 1878,
            national = false,
            venue = "Old Trafford",
            venueCapacity = 76212,
            venueCity = "Manchester",
            venueAddress = "Sir Matt Busby Way, Stretford, Manchester M16 0RA, UK",
            venueSurface = "grass",
            venueImageUrl = "https://media.api-football.com/teams/33.png",
            leagueId = 2,
            crestUrl = "https://media.api-football.com/teams/33.png",
            uid = 2
        )
        clubDao.insert(club2)
        val clubItem: List<Club> = clubDao.getClubsByLeagueId(2).first()
        Assert.assertEquals(clubItem.size, 2)
    }

    @Test
    @Throws(Exception::class)
    fun deleteByLeagueId() = runBlocking {
        val club = Club(
            name = "Arsenal FC",
            country = "England",
            code = "ARS",
            founded = 1886,
            national = false,
            venue = "Emirates Stadium",
            venueCapacity = 60355,
            venueCity = "London",
            venueAddress = "Hornsey Rd, London N7 7AJ, UK",
            venueSurface = "grass",
            venueImageUrl = "https://media.api-football.com/teams/42.png",
            leagueId = 2,
            crestUrl = "https://media.api-football.com/teams/42.png",
            uid = 1
        )
        clubDao.insert(club)
        val club2 = Club(
            name = "Manchester United FC",
            country = "England",
            code = "MUN",
            founded = 1878,
            national = false,
            venue = "Old Trafford",
            venueCapacity = 76212,
            venueCity = "Manchester",
            venueAddress = "Sir Matt Busby Way, Stretford, Manchester M16 0RA, UK",
            venueSurface = "grass",
            venueImageUrl = "https://media.api-football.com/teams/33.png",
            leagueId = 2,
            crestUrl = "https://media.api-football.com/teams/33.png",
            uid = 2
        )
        clubDao.insert(club2)
        clubDao.deleteClubsByLeagueId(2)
        val clubItem: List<Club> = clubDao.getClubsByLeagueId(2).first()
        Assert.assertEquals(clubItem.size, 0)
    }

    @Test
    @Throws(Exception::class)
    fun getAllClubs() = runBlocking {
        val club = Club(
            name = "Arsenal FC",
            country = "England",
            code = "ARS",
            founded = 1886,
            national = false,
            venue = "Emirates Stadium",
            venueCapacity = 60355,
            venueCity = "London",
            venueAddress = "Hornsey Rd, London N7 7AJ, UK",
            venueSurface = "grass",
            venueImageUrl = "https://media.api-football.com/teams/42.png",
            leagueId = 2,
            crestUrl = "https://media.api-football.com/teams/42.png",
            uid = 1
        )
        clubDao.insert(club)
        val club2 = Club(
            name = "Barcelona FC",
            country = "Spain",
            code = "BAR",
            founded = 1899,
            national = false,
            venue = "Camp Nou",
            venueCapacity = 99787,
            venueCity = "Barcelona",
            venueAddress = "C. d'Arístides Maillol, 12, 08028 Barcelona, Spain",
            venueSurface = "grass",
            venueImageUrl = "https://media.api-football.com/teams/529.png",
            leagueId = 87,
            crestUrl = "https://media.api-football.com/teams/529.png",
            uid = 2
        )
        clubDao.insert(club2)
        val clubItem: List<Club> = clubDao.getAllClubs().first()
        Assert.assertEquals(clubItem.size, 2)
    }


}