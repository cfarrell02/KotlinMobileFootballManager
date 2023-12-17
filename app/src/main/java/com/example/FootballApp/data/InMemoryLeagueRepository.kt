package com.example.FootballApp.data


import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.map
import org.setu.model.League

class InMemoryLeagueRepository : LeagueRepository {
    private val _leagues = MutableStateFlow<MutableList<League>>(mutableListOf())

    override suspend fun addLeague(league: League) {
        _leagues.value.add(league)
        _leagues.value = _leagues.value.toMutableList() // Emit a new list to the Flow
    }

    override fun getLeagueById(id: String): Flow<League> {
        return _leagues.map { leagues ->
            leagues.first { it.uid == id }
        }
    }

    override suspend fun deleteLeague(league: League) {
        _leagues.value.remove(league)
        _leagues.value = _leagues.value.toMutableList() // Emit a new list to the Flow
    }

    override suspend fun updateLeague(league: League) {
        val index = _leagues.value.indexOfFirst { it.uid == league.uid }
        if (index != -1) {
            _leagues.value[index] = league
            _leagues.value = _leagues.value.toMutableList() // Emit a new list to the Flow
        }
    }

    override fun getLeagues(): Flow<List<League>> {
        return _leagues
    }
}
