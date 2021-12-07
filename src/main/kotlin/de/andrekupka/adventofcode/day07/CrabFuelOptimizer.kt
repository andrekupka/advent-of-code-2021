package de.andrekupka.adventofcode.day07

import kotlin.math.abs

data class PositionWithFuel(
    val position: Int,
    val fuelUsage: Int,
)

fun optimizeFuelUsageByEstimation(initialPositions: List<Int>, fuelEstimation: (Int, Int) -> Int): PositionWithFuel {
    require(initialPositions.isNotEmpty()) {
        "initial positions must not be empty"
    }

    val possiblePositions = initialPositions.toSet()

    return possiblePositions.map { targetPosition ->
        val fuelUsage = initialPositions.sumOf { initialPosition ->
            fuelEstimation(initialPosition, targetPosition)
        }
        PositionWithFuel(targetPosition, fuelUsage)
    }.minByOrNull { it.fuelUsage }!!
}

fun optimizeConstantFuelUsage(
    initialPositions: List<Int>
) = optimizeFuelUsageByEstimation(initialPositions) { initialPosition, targetPosition ->
    abs(targetPosition - initialPosition)
}