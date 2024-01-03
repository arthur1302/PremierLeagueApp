package com.example.premierleagueapp.data

import com.example.premierleagueapp.R
import kotlin.random.Random

data class TeamMockData(val name: String, val country: String = "", val imageResourceId: Int = 0) {
    companion object TeamSampler {
        private val sampleTeams = listOf(
            "Man City",
            "Man Utd",
            "Chelsea",
            "Liverpool",
        )

        private val sampleDescription = listOf(
            "Manchester",
            "Manchester",
            "London",
            "Liverpool",
        )

        private val sampleLogo = listOf(
            R.drawable.city,
            R.drawable.united,
            R.drawable.chelsea,
            R.drawable.liverpool,
        )

        val getOne: () -> TeamMockData = {
            val random = Random.nextInt(0, sampleTeams.size)
            TeamMockData(
                sampleTeams[random],
                sampleDescription[random],
                sampleLogo[random],
            )
        }

        val getAll: () -> List<TeamMockData> = {
            val list = mutableListOf<TeamMockData>()
            repeat(10) {
                for (i in sampleTeams.indices) {
                    list.add(
                        TeamMockData(
                            sampleTeams[i],
                            sampleDescription[i],
                            sampleLogo[i],
                        ),
                    )
                }
            }
            list
        }
    }
}
