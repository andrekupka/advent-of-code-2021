package de.andrekupka.adventofcode.day05

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
        if (horizontal) {
            progression(start.x, end.x).map { Point(it, start.y) }
        } else if (vertical) {
            progression(start.y, end.y).map { Point(start.x, it) }
        } else {
            error("Only horizontal or vertical lines can be mapped to points.")
        }
    }

    private fun progression(start: Int, end: Int) =
        if (start < end) {
            start..end
        } else {
            start downTo end
        }
}
