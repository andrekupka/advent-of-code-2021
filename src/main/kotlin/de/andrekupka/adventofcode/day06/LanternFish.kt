package de.andrekupka.adventofcode.day06

data class LanternFish(
    val remainingDaysUntilReproduction: Int,
) {

    fun reproduce() = if (remainingDaysUntilReproduction == 0) {
        listOf(LanternFish(6), LanternFish(8))
    } else {
        listOf(LanternFish(remainingDaysUntilReproduction - 1))
    }
}