package de.andrekupka.adventofcode.utils

fun <T> List<T>.head() = take(1).single()

fun <T> List<T>.tail() = drop(1)

infix fun IntRange.cross(other: IntRange) = flatMap { first ->
    other.map { second -> first to second }
}
