package com.example.premierleagueapp.data.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface MatchDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(match: DbMatch)

    @Query("delete from matches")
    fun drop()

    @Query("SELECT * FROM matches WHERE status != 'FINISHED' LIMIT 5")
    fun getAllMatches(): Flow<List<DbMatch>>
}
