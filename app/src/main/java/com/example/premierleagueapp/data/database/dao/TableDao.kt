package com.example.premierleagueapp.data.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.premierleagueapp.data.database.entities.DbTable
import kotlinx.coroutines.flow.Flow

@Dao
interface TableDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(item: DbTable)

    @Query("Select * from standings")
    fun getAllStandings(): Flow<List<DbTable>>
}
