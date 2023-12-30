package com.example.premierleagueapp.data.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface TeamDao {
    @Query("SELECT * from teams ORDER BY name ASC")
    fun getAllItems(): Flow<List<TeamDatabase>>

    @Query("SELECT * from teams where id = :id")
    fun getItem(id: Int): Flow<TeamDatabase>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertTeam(item: TeamDatabase)
}
