package com.example.premierleagueapp.data.database.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.premierleagueapp.model.Competition
import com.example.premierleagueapp.model.Match
import com.example.premierleagueapp.model.Team

/**
 * Entity to create a table for matches
 *
 * @author Arthur Haus
 *
 */
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

/**
 * Converts a [DbMatch] instance in a [Match] instance
 *
 * @receiver an instance of [DbMatch]
 * @return an instance of [Match]
 */
fun DbMatch.asDomainMatch() = Match(id, competition, status, utcDate, homeTeam, awayTeam)

/**
 * Converts a [List] of [DbMatch] in a [List] of [Match]
 *
 * @receiver a [List] of [DbMatch]
 * @return a [List] of [Match]
 */
fun List<DbMatch>.asDomainMatches() = map { it.asDomainMatch() }

/**
 * Converts a [Match] instance in a [DbMatch] instance
 *
 * @receiver an instance of [Match]
 * @return an instance of [DbMatch]
 */
fun Match.asDbMatch(): DbMatch = DbMatch(id, competition, status, utcDate, homeTeam, awayTeam)
