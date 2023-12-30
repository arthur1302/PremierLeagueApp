package com.example.premierleagueapp.data.database

import androidx.room.TypeConverter
import com.example.premierleagueapp.model.Player
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class PlayerListConverter {
    @TypeConverter
    fun fromString(value: String): List<Player> {
        val listType = object : TypeToken<List<Player>>() {}.type
        return Gson().fromJson(value, listType)
    }

    @TypeConverter
    fun toString(value: List<Player>): String {
        val gson = Gson()
        return gson.toJson(value)
    }
}
