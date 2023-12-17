package com.example.FootballApp

import android.content.Context
import com.example.FootballApp.data.LeagueDatabase
import com.example.FootballApp.data.PersistentLeagueRepository
import com.example.FootballApp.data.LeagueRepository
import com.example.FootballApp.data.leagueDao

interface AppContainer {

    val leagueRepository: LeagueRepository
}

class DefaultAppContainer(private val context: Context): AppContainer {
    override val leagueRepository: LeagueRepository by lazy {
        PersistentLeagueRepository(LeagueDatabase.getDataBase(context = context).leagueDao())
    }
}