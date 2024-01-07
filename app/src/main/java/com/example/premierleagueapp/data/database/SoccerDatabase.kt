package com.example.premierleagueapp.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.premierleagueapp.data.database.converters.CoachConverter
import com.example.premierleagueapp.data.database.converters.CompetitionConverter
import com.example.premierleagueapp.data.database.converters.PlayerListConverter
import com.example.premierleagueapp.data.database.converters.TeamConverter
import com.example.premierleagueapp.data.database.dao.MatchDao
import com.example.premierleagueapp.data.database.dao.TableDao
import com.example.premierleagueapp.data.database.dao.TeamDao
import com.example.premierleagueapp.data.database.entities.DbMatch
import com.example.premierleagueapp.data.database.entities.DbTable
import com.example.premierleagueapp.data.database.entities.DbTeam

/**
 * Creation of a database
 *
 * @author Arthur Haus
 */
@Database(entities = [DbTeam::class, DbMatch::class, DbTable::class], version = 1, exportSchema = false)
@TypeConverters(CoachConverter::class, PlayerListConverter::class, TeamConverter::class, CompetitionConverter::class)
abstract class SoccerDatabase : RoomDatabase() {
    abstract fun teamDao(): TeamDao
    abstract fun matchDao(): MatchDao
    abstract fun tableDao(): TableDao
}
