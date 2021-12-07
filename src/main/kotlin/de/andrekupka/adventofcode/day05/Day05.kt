package de.andrekupka.adventofcode.day05

import com.github.h0tk3y.betterParse.grammar.parseToEnd
import de.andrekupka.adventofcode.utils.readResourceLinesNotBlank

fun computeCoverageWithAtLeastTwoOverlaps(opaqueCloudLines: List<OpaqueCloudLine>): Int {
    val coverage = OpaqueCloudLineCoverage()
    opaqueCloudLines.forEach { coverage.recordLine(it) }

    return coverage.countPointsWithMinimumNumberOfOverlaps(2)
}

fun main() {
    val lines = readResourceLinesNotBlank("/day05/input").map { it.trim() }

    val opaqueCloudLines = lines.map { opaqueCloudLineParser.parseToEnd(it) }

    val edgeAdjacentLines = opaqueCloudLines.filter { it.vertical or it.horizontal }

    val edgeAdjacentCoverage = computeCoverageWithAtLeastTwoOverlaps(edgeAdjacentLines)
    println("There are $edgeAdjacentCoverage points in edge adjacent lines with at least two overlaps")

    val overallCoverage = computeCoverageWithAtLeastTwoOverlaps(opaqueCloudLines)
    println("There are $overallCoverage points with at least two overlaps")
}