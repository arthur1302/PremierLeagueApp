package com.example.premierleagueapp.data.database

import androidx.room.TypeConverter
import com.example.premierleagueapp.model.Coach

class CoachConverter {
    @TypeConverter
    fun fromCoach(coach: Coach): String {
        // Implementeer de logica om Coach naar een String te converteren
        return "${coach.name},${coach.nationality}"
    }

    @TypeConverter
    fun toCoach(coachString: String): Coach {
        // Implementeer de logica om een String naar een Coach-object te converteren
        val (name, nationality) = coachString.split(",")
        return Coach(name, nationality)
    }
}
