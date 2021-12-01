package de.andrekupka.adventofcode.utils

class InputResourceNotFoundException(relativeResourcePath: String) :
    RuntimeException("Input at location $relativeResourcePath was not found")

fun readResourceContent(path: String) = object {}.javaClass.getResource(path)?.readText()
    ?: throw InputResourceNotFoundException(path)

fun readResourceLines(path: String) = readResourceContent(path).lines()

fun readResourceLinesNotBlank(path: String) = readResourceLines(path).filter { it.isNotBlank() }

fun <T> readResourceLinesMapNotBlank(path: String, transform: (String) -> T) = readResourceLinesNotBlank(path)
    .map { transform(it) }