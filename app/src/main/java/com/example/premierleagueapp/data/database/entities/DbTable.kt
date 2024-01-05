package com.example.premierleagueapp.data.database.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.premierleagueapp.model.Table
import com.example.premierleagueapp.model.Team

@Entity(tableName = "standings")
data class DbTable(
    @PrimaryKey(autoGenerate = true)
    val position: Int,
    val team: Team,
    val points: Int,
)

fun Table.asDbTable(): DbTable = DbTable(position, team, points)

fun DbTable.asDomainTable() = Table(position, team, points)

fun List<DbTable>.asDomainTables() = map { it.asDomainTable() }
