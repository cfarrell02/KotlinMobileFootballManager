package org.setu.model


import java.io.File


class LeagueStore {
    private val _leagues = mutableListOf<League>()
    var isDirty = false


    val leagues: List<League>
        get() = _leagues

    fun addLeague(name: String, country: String, crestUrl: String): League {
        isDirty = true
        require(name != "") { "Name cannot be blank" }
        require(country != "") { "Country cannot be blank" }
        val league = League(name, country, crestUrl = crestUrl)
        _leagues.add(league)
        return league
    }


    fun removeLeague(uid: String): League {
        isDirty = true
        val league = _leagues.find { it.uid == uid } ?: throw IllegalArgumentException("League with uid $uid not found")
        _leagues.removeIf { it.uid == uid }
        return league
    }

    fun clear() {
        _leagues.clear()
    }

    fun updateLeague(uid: String, name: String, country: String): League {
        isDirty = true
        val league = _leagues.find { it.uid == uid }!!
        require(name != "") { "Name cannot be blank" }
        require(country != "") { "Country cannot be blank" }
        league.name = name
        league.country = country
        return league
    }

    fun search(searchTerm: String): List<Any> {
        val searchResults = mutableListOf<Any>()
        //Big bulky inefficient triple nested loop
        //Could I make this more efficient? Yes but i dont want to
        leagues.forEach { league ->
            if (league.toString().contains(searchTerm, ignoreCase = true)) {
                searchResults.add(league)
            }
            league.clubs.forEach { club ->
                if (club.toString().contains(searchTerm, ignoreCase = true)) {
                    searchResults.add(club)
                }
                club.people.forEach { player ->
                    if (player.toString().contains(searchTerm, ignoreCase = true)) {
                        searchResults.add(player)
                    }
                }
            }
        }
        return searchResults
    }

//    fun load(fileName : String = "leagues.json") : List<League> {
//        val file = File(fileName)
//        // Use GSON to load the array from a file
//        val jsonArray = file.readText()
//
//        val gson = GsonBuilder()
//            .registerTypeAdapter(Person::class.java, PersonTypeAdapter())
//            .create()
//        val leaguesArray = gson.fromJson(jsonArray, Array<League>::class.java)
//
//        _leagues.clear()
//        _leagues.addAll(leaguesArray)
//        return leaguesArray.toList()
//
//    }
//
//    fun save(fileName : String = "leagues.json"){
//        val file = File(fileName)
//
//        // Create a Gson instance with the custom PersonTypeAdapter
//        val gson = GsonBuilder()
//            .registerTypeAdapter(Person::class.java, PersonTypeAdapter())
//            .create()
//
//        // Serialize the leagues list to JSON
//        val jsonArray = gson.toJson(_leagues)
//
//        // Write the JSON data to the file
//        file.writeText(jsonArray)
//    }

    fun getLeague(uid: String): League? {
        return leagues.find { it.uid == uid }
    }






}