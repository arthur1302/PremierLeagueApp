package com.example.premierleagueapp.data.database

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.example.premierleagueapp.model.Coach
import com.example.premierleagueapp.model.Player
import com.example.premierleagueapp.network.Team

@Entity(tableName = "teams")
@TypeConverters(CoachConverter::class, PlayerListConverter::class)
data class TeamDatabase(
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0,
    var name: String = "",
    var shortName: String = "",
    var crest: String = "",
    var website: String = "",
    var tla: String = "",
    var coach: Coach = Coach("", ""),
    var venue: String = "",
    var squad: List<Player> = listOf(),
)

fun TeamDatabase.asDomainTeam() = Team(id = id, name = name, shortName = shortName, crest = crest, website = website, tla = tla, coach = coach, venue = venue, squad = squad)
fun List<TeamDatabase>.asDomainTeams() = map { it.asDomainTeam() }

fun Team.asDbTeam() = TeamDatabase(id, name, shortName, crest, website, tla, coach, venue, squad)
