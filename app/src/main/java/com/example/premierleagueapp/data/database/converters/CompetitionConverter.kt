package com.example.premierleagueapp.data.database.converters

import androidx.room.TypeConverter
import com.example.premierleagueapp.model.Competition
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

/**
 * Converter for a Competition item
 *
 * @author Arthur Haus
 *
 */
class CompetitionConverter {
    private val gson = Gson()

    @TypeConverter
    fun fromCompetition(competition: Competition): String {
        return gson.toJson(competition)
    }

    @TypeConverter
    fun toCompetition(competitionString: String): Competition {
        val type = object : TypeToken<Competition>() {}.type
        return gson.fromJson(competitionString, type)
    }
}
