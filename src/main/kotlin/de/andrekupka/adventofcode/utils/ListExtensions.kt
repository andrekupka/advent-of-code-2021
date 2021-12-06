package de.andrekupka.adventofcode.utils

fun <T> List<T>.head() = take(1).single()

fun <T> List<T>.tail() = drop(1)