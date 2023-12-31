package com.example.premierleagueapp.data.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface TeamDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(item: DbTeam)

    @Query("Select * from teams order by name asc")
    fun getAllTeams(): Flow<List<DbTeam>>

    @Query("SELECT * from teams WHERE id = :id")
    fun getTeam(id: Int): Flow<DbTeam>
}
