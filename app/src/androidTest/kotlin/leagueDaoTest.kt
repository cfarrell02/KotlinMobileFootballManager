
import android.content.Context
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.league.data.LeagueDao
import com.example.league.data.LeagueDatabase
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import org.junit.*
import org.junit.runner.RunWith
import org.setu.model.League
import java.io.IOException

@RunWith(AndroidJUnit4::class)
class LeagueDaoTest{
    private lateinit var leagueDao: LeagueDao
    private lateinit var db: LeagueDatabase

    @Before
    fun createDb() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        db = Room.inMemoryDatabaseBuilder(context, LeagueDatabase::class.java).allowMainThreadQueries().build()
        leagueDao = db.leagueDao()
    }

    @After
    @Throws(IOException::class)
    fun closeDb() {
        db.close()
    }

    @Test
    @Throws(Exception::class)
    fun insertAndGetLeague() = runBlocking {
        val league = League(name = "Premier League", country = "England", type = "League", countryFlagUrl = "https://media.api-football.com/flags/gb.svg", crestUrl = "https://media.api-football.com/leagues/2.png", uid = 2)
        leagueDao.insert(league)
        val leagueItem: League? = leagueDao.getLeague(2).first()
        Assert.assertEquals(leagueItem?.name, "Premier League")
    }

    @Test
    @Throws(Exception::class)
    fun deleteLeague()  = runBlocking{
        val league = League(name = "Premier League", country = "England", type = "League", countryFlagUrl = "https://media.api-football.com/flags/gb.svg", crestUrl = "https://media.api-football.com/leagues/2.png", uid = 2)
        leagueDao.insert(league)
        leagueDao.delete(league)
        val leagueItem: League? = leagueDao.getLeague(2).first()
        Assert.assertEquals(leagueItem, null)
    }

    @Test
    @Throws(Exception::class)
    fun updateLeague() = runBlocking {
        val league = League(name = "Premier League", country = "England", type = "League", countryFlagUrl = "https://media.api-football.com/flags/gb.svg", crestUrl = "https://media.api-football.com/leagues/2.png", uid = 2)
        leagueDao.insert(league)
        leagueDao.update(League(name = "La Liga", country = "Spain", type = "League", countryFlagUrl = "https://media.api-football.com/flags/es.svg", crestUrl = "https://media.api-football.com/leagues/87.png", uid = 2))
        val leagueItem: League? = leagueDao.getLeague(2).first()
        Assert.assertEquals(leagueItem?.name, "La Liga")
    }

    @Test
    @Throws(Exception::class)
    fun getAllLeagues() = runBlocking {
        val league = League(name = "Premier League", country = "England", type = "League", countryFlagUrl = "https://media.api-football.com/flags/gb.svg", crestUrl = "https://media.api-football.com/leagues/2.png", uid = 2)
        leagueDao.insert(league)
        val league2 = League(name = "La Liga", country = "Spain", type = "League", countryFlagUrl = "https://media.api-football.com/flags/es.svg", crestUrl = "https://media.api-football.com/leagues/87.png", uid = 3)
        leagueDao.insert(league2)
        val leagueItem: List<League> = leagueDao.getAllLeagues().first()
        Assert.assertEquals(leagueItem.size, 2)
    }



}