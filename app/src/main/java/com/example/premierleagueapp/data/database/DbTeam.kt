package com.example.premierleagueapp.data.database

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.premierleagueapp.model.Coach
import com.example.premierleagueapp.model.Player
import com.example.premierleagueapp.network.Team

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

@Entity(tableName = "coach")
data class DbCoach(
    val name: String,
    val nationality: String,
)

@Entity(tableName = "players")
data class DbPlayer(
    val name: String,
    val position: String,
    val nationality: String,
)

fun Team.asDbTeam(): DbTeam = DbTeam(id, name, shortName, crest, website, tla, coach, venue, squad)
fun DbTeam.asDomainTeam() = Team(id, name, shortName, crest, website, tla, coach, venue, squad)
fun List<DbTeam>.asDomainTeams() = map { it.asDomainTeam() }
