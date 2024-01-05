package com.example.premierleagueapp.data.database.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.premierleagueapp.model.Table
import com.example.premierleagueapp.model.Team

/**
 * Entity to create a table for standings
 *
 * @author Arthur Haus
 *
 */
@Entity(tableName = "standings")
data class DbTable(
    @PrimaryKey(autoGenerate = true)
    val position: Int,
    val team: Team,
    val points: Int,
)

/**
 * Converts a [Table] instance in a [DbTable] instance
 *
 * @receiver an instance of [Table]
 * @return an instance of [DbTable]
 */
fun Table.asDbTable(): DbTable = DbTable(position, team, points)

/**
 * Converts a [DbTable] instance in a [Table] instance
 *
 * @receiver an instance of [DbTable]
 * @return an instance of [Table]
 */
fun DbTable.asDomainTable() = Table(position, team, points)

/**
 * Converts a [List] of [DbTable] in a [List] of [Table]
 *
 * @receiver a [List] of [DbTable]
 * @return a [List] of [Table]
 */
fun List<DbTable>.asDomainTables() = map { it.asDomainTable() }
