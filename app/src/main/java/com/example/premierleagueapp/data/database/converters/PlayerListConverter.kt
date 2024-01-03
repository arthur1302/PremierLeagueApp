package com.example.premierleagueapp.data.database.converters

import androidx.room.TypeConverter
import com.example.premierleagueapp.model.Player
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class PlayerListConverter {
    @TypeConverter
    fun fromPlayerList(playerList: List<Player>): String {
        val gson = Gson()
        return gson.toJson(playerList)
    }

    @TypeConverter
    fun toPlayerList(playerListString: String): List<Player> {
        val gson = Gson()
        val type = object : TypeToken<List<Player>>() {}.type
        return gson.fromJson(playerListString, type)
    }
}
