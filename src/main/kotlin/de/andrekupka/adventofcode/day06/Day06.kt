package de.andrekupka.adventofcode.day06

import de.andrekupka.adventofcode.utils.readResourceLinesNotBlank

fun computePopulation(initialFishTimers: List<Int>, days: Int): Int {
    val initialFishes = initialFishTimers.map { LanternFish(it) }

    val finalFishes = (0 until days).fold(initialFishes) { fishes, _ ->
        fishes.map { it.reproduce() }.flatten()
    }

    return finalFishes.size
}

fun computePopulationEfficient(initialFishTimers: List<Int>, days: Int): Long {
    val state = initialFishTimers.groupBy { it }.mapValues { it.value.count().toLong() }
    val initialPopulation = LanternFishPopulation(state)

    val finalPopulation = (0 until days).fold(initialPopulation) { population, _ ->
        population.reproduce()
    }

    return finalPopulation.size
}

fun main() {
    val line = readResourceLinesNotBlank("/day06/input").single()

    val initialFishTimers = line.split(",").map { it.toInt() }

    val firstDays = 80
    val firstPopulation = computePopulation(initialFishTimers, firstDays)

    println("After $firstDays days there are $firstPopulation lantern fishes.")

    val secondDays = 256
    val secondPopulation = computePopulationEfficient(initialFishTimers, secondDays)

    println("After $secondDays days there are $secondPopulation lantern fishes.")
}