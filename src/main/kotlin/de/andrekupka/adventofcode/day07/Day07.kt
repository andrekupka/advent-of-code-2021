package de.andrekupka.adventofcode.day07

import de.andrekupka.adventofcode.utils.readResourceLinesNotBlank

fun main() {
    val lines = readResourceLinesNotBlank("/day07/input")

    val crabPositions = lines.single().split(",").map { it.toInt() }

    val constantPositionAndFuel = optimizeConstantFuelUsage(crabPositions)
    println("Optimal position ${constantPositionAndFuel.position} takes ${constantPositionAndFuel.fuelUsage} fuel with linear fuel usage")

    val linearPositionAndFuel = optimizeLinearFuelUsage(crabPositions)
    println("Optimal position ${linearPositionAndFuel.position} takes ${linearPositionAndFuel.fuelUsage} fuel with constant fuel usage")
}