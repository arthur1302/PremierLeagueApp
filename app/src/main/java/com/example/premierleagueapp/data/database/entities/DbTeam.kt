package com.example.premierleagueapp.data.database.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.premierleagueapp.model.Coach
import com.example.premierleagueapp.model.Player
import com.example.premierleagueapp.model.Team

/**
 * Entity to create a table for teams
 *
 * @author Arthur Haus
 *
 */
@Entity(tableName = "teams")
data class DbTeam(
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0,
    var name: String,
    var shortName: String,
    var crest: String,
    var website: String,
    var tla: String,
    var coach: Coach,
    var venue: String,
    var squad: List<Player>,
)

/**
 * Converts a [Team] instance in a [DbTeam] instance
 *
 * @receiver an instance of [Team]
 * @return an instance of [DbTeam]
 */
fun Team.asDbTeam(): DbTeam = DbTeam(id, name, shortName, crest, website, tla, coach, venue, squad)

/**
 * Converts a [DbTeam] instance in a [Team] instance
 *
 * @receiver an instance of [DbTeam]
 * @return an instance of [Team]
 */
fun DbTeam.asDomainTeam() = Team(id, name, shortName, crest, website, tla, coach, venue, squad)

/**
 * Converts a [List] of [DbTeam] in a [List] of [Team]
 *
 * @receiver a [List] of [DbTeam]
 * @return a [List] of [Team]
 */
fun List<DbTeam>.asDomainTeams() = map { it.asDomainTeam() }
