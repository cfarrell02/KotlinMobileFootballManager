import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.FootballApp.FootballApp
import com.example.FootballApp.data.LeagueRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import org.setu.model.League

class LeagueViewModel(private val leagueRepository: LeagueRepository) : ViewModel() {

    val leagues = mutableStateOf<List<League>>(emptyList())

    init {
        refreshLeagues()
    }

    fun onLeagueClicked(league: League) {
        // Handle click if needed
    }

    fun addLeague(league: League) {
        leagueRepository.addLeague(league)
        refreshLeagues()
    }

    private fun refreshLeagues() {
        leagues.value = leagueRepository.getLeagues()
    }

    companion object {
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val application = (this[ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY] as FootballApp)
                val leagueRepository = application.container.leagueRepository
                LeagueViewModel(leagueRepository = leagueRepository)
            }
        }
    }
}
