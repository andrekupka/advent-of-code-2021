package de.andrekupka.adventofcode.day05

import kotlin.math.abs
import kotlin.math.max

data class Point(
    val x: Int,
    val y: Int,
)

data class OpaqueCloudLine(
    val start: Point,
    val end: Point,
) {
    val horizontal = start.y == end.y
    val vertical = start.x == end.x

    val points by lazy {
        val xProgression = progression(start.x, end.x)
        val yProgression = progression(start.y, end.y)

        val stretchSize = max(xProgression.size, yProgression.size)

        val xElements = xProgression.stretchToList(stretchSize)
        val yElements = yProgression.stretchToList(stretchSize)

        (xElements zip yElements).map { Point(it.first, it.second) }
    }

    private fun progression(start: Int, end: Int) =
        if (start < end) {
            start..end
        } else {
            start downTo end
        }

    private val IntProgression.size get() = abs(last - first) + 1

    private fun IntProgression.stretchToList(stretchSize: Int) = if (size == stretchSize) {
        this
    } else {
        List(stretchSize) { first }
    }
}
