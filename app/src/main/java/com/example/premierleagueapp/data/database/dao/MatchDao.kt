package com.example.premierleagueapp.data.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.premierleagueapp.data.database.entities.DbMatch
import kotlinx.coroutines.flow.Flow

/**
 * Dao for matches
 *
 * @author Arthur Haus
 *
 */
@Dao
interface MatchDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(match: DbMatch)

    @Query("DELETE FROM matches")
    fun drop()

    @Query("SELECT * FROM matches WHERE status != 'FINISHED' LIMIT 5")
    fun getAllMatches(): Flow<List<DbMatch>>
}
