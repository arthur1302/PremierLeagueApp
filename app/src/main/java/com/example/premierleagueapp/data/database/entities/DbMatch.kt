package com.example.premierleagueapp.data.database.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.premierleagueapp.model.Competition
import com.example.premierleagueapp.network.Match
import com.example.premierleagueapp.network.Team

@Entity(tableName = "matches")
data class DbMatch(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val competition: Competition,
    val status: String,
    val utcDate: String,
    val homeTeam: Team,
    val awayTeam: Team,
)

fun DbMatch.asDomainMatch() = Match(id, competition, status, utcDate, homeTeam, awayTeam)
fun List<DbMatch>.asDomainMatches() = map { it.asDomainMatch() }

fun Match.asDbMatch(): DbMatch = DbMatch(id, competition, status, utcDate, homeTeam, awayTeam)
