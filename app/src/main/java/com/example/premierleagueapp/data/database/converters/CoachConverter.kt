package com.example.premierleagueapp.data.database.converters

import androidx.room.TypeConverter
import com.example.premierleagueapp.model.Coach

/**
 * Converter for a Coach item
 *
 * @author Arthur Haus
 */
class CoachConverter {
    @TypeConverter
    fun fromCoach(coach: Coach): String {
        return "${coach.name},${coach.nationality}"
    }

    @TypeConverter
    fun toCoach(coachString: String): Coach {
        val (name, nationality) = coachString.split(",")
        return Coach(name, nationality)
    }
}
