package data

import com.example.premierleagueapp.R
import kotlin.random.Random

data class Team(val name: String, val country: String = "", val imageResourceId: Int = 0) {
    companion object TeamSampler {
        val sampleTeams = listOf(
            "Man City",
            "Man Utd",
            "Chelsea",
            "Liverpool",
        )

        val sampleDescription = listOf(
            "Manchester",
            "Manchester",
            "London",
            "Liverpool",
        )

        val sampleLogo = listOf(
            R.drawable.city,
            R.drawable.united,
            R.drawable.chelsea,
            R.drawable.liverpool,
        )

        val getOne: () -> Team = {
            val random = Random.nextInt(0, sampleTeams.size)
            Team(
                sampleTeams[random],
                sampleDescription[random],
                sampleLogo[random],
            )
        }
    }
}
