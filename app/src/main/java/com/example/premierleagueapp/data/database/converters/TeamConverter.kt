package com.example.premierleagueapp.data.database.converters

import androidx.room.TypeConverter
import com.example.premierleagueapp.model.Team
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

/**
 * Converter for a Team item
 *
 * @author Arthur Haus
 */
class TeamConverter {
    private val gson = Gson()

    @TypeConverter
    fun fromTeam(team: Team): String {
        return gson.toJson(team)
    }

    @TypeConverter
    fun toTeam(teamString: String): Team {
        val type = object : TypeToken<Team>() {}.type
        return gson.fromJson(teamString, type)
    }
}
