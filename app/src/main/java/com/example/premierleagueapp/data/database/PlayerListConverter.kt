package com.example.premierleagueapp.data.database

import androidx.room.TypeConverter
import com.example.premierleagueapp.model.Player
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class PlayerListConverter {
    @TypeConverter
    fun fromPlayerList(playerList: List<Player>): String {
        // Implementeer de logica om een lijst van spelers naar een String te converteren
        val gson = Gson()
        return gson.toJson(playerList)
    }

    @TypeConverter
    fun toPlayerList(playerListString: String): List<Player> {
        // Implementeer de logica om een String naar een lijst van spelers te converteren
        val gson = Gson()
        val type = object : TypeToken<List<Player>>() {}.type
        return gson.fromJson(playerListString, type)
    }
}
