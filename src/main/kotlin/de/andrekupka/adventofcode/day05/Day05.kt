package de.andrekupka.adventofcode.day05

import com.github.h0tk3y.betterParse.grammar.parseToEnd
import de.andrekupka.adventofcode.utils.readResourceLinesNotBlank

fun main() {
    val lines = readResourceLinesNotBlank("/day05/input").map { it.trim() }

    val opaqueCloudLines = lines.map { opaqueCloudLineParser.parseToEnd(it) }

    val edgeAdjacentLines = opaqueCloudLines.filter { it.vertical or it.horizontal }

    val coverage = OpaqueCloudLineCoverage()
    edgeAdjacentLines.forEach { coverage.recordLine(it) }

    val numberOfPointsWithAtLeastTwoOverlaps = coverage.countPointsWithMinimumNumberOfOverlaps(2)

    println("There are $numberOfPointsWithAtLeastTwoOverlaps points with at least two overlaps")
}