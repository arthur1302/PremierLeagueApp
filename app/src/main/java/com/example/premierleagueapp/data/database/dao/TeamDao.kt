package com.example.premierleagueapp.data.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.premierleagueapp.data.database.entities.DbTeam
import kotlinx.coroutines.flow.Flow

/**
 * Dao for teams
 *
 * @author Arthur Haus
 *
 */
@Dao
interface TeamDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(item: DbTeam)

    @Query("SELECT * FROM teams ORDER BY name ASC")
    fun getAllTeams(): Flow<List<DbTeam>>

    @Query("SELECT * FROM teams WHERE id = :id")
    fun getTeam(id: Int): Flow<DbTeam>
}
