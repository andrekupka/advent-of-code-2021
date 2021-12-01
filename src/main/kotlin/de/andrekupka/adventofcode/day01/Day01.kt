package de.andrekupka.adventofcode.day01

import de.andrekupka.adventofcode.utils.readResourceLinesMapNotBlank

fun <T> List<T>.countSubsequentTuplesThatFulfill(predicate: (T, T) -> Boolean): Int =
    windowed(size = 2, step = 1).count { predicate(it[0], it[1]) }

fun main() {
    val measurements = readResourceLinesMapNotBlank("/day01/input") { it.toLong() }

    val numberOfIncreases = measurements.countSubsequentTuplesThatFulfill { a, b -> b > a }

    println("There are $numberOfIncreases measurements that are greater than the previous measurement")

    val aggregatedWindowedMeasurements = measurements.windowed(size = 3, step = 1).map { it.sum() }
    val numberOfWindowedIncreases = aggregatedWindowedMeasurements.countSubsequentTuplesThatFulfill { a , b -> b > a }

    println("There are $numberOfWindowedIncreases windowed measurements that are greater than the previous measurement")
}