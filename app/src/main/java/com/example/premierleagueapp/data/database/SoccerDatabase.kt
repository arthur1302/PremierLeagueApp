package com.example.premierleagueapp.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters

@Database(entities = [DbTeam::class, DbMatch::class], version = 1)
@TypeConverters(CoachConverter::class, PlayerListConverter::class, TeamConverter::class, CompetitionConverter::class)
abstract class SoccerDatabase : RoomDatabase() {
    abstract fun teamDao(): TeamDao
    abstract fun matchDao(): MatchDao
}
