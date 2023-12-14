package com.example.FootballApp.data

import org.setu.model.League

class InMemoryLeagueRepository: LeagueRepository {
    private var leagues: MutableList<League> = mutableListOf()
    override fun addLeague(league: League) {
        leagues.add(league)
    }

    override fun updateLeague(league: League) {
        val index = leagues.indexOfFirst { it.uid == league.uid }
        if (index != -1) {
            leagues[index] = league
        }
    }

    override fun getLeagues(): List<League> {
        return leagues
    }

}