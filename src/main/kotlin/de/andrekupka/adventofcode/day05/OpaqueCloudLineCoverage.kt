package de.andrekupka.adventofcode.day05

class OpaqueCloudLineCoverage {

    private val pointToCounterMap = mutableMapOf<Point, Int>()

    fun recordLine(line: OpaqueCloudLine) {
        line.points.forEach { point ->
            pointToCounterMap.compute(point) { _, count -> (count ?: 0) + 1 }
        }
    }

    fun countPointsWithMinimumNumberOfOverlaps(minimumNumberOfOverlaps: Int): Int {
        require(minimumNumberOfOverlaps > 0)

        return pointToCounterMap.count { it.value >= minimumNumberOfOverlaps }
    }
}