package com.example.FootballApp

import com.example.FootballApp.data.InMemoryLeagueRepository
import com.example.FootballApp.data.LeagueRepository

interface AppContainer {

    val leagueRepository: LeagueRepository
}

class DefaultAppContainer: AppContainer {
    override val leagueRepository: LeagueRepository by lazy {
        InMemoryLeagueRepository()
    }
}