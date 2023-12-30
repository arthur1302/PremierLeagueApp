package com.example.premierleagueapp.data.database

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [TeamDatabase::class], version = 1)
abstract class SoccerDatabase : RoomDatabase() {
    abstract fun teamDao(): TeamDao
}
