package com.example.FootballApp.data

import org.setu.model.League

interface LeagueRepository {

    fun addLeague(league: League)

    fun updateLeague(league: League)

    fun getLeagues(): List<League>
}