package de.andrekupka.adventofcode.utils

fun groupByBlankLines(lines: List<String>): List<List<String>> {
    class Accumulator {
        val result = mutableListOf<List<String>>()
        lateinit var currentGroup: MutableList<String>

        private var startNewGroup = true

        fun accumulate(line: String): Accumulator {
            if (line.isBlank()) {
                startNewGroup = true
            } else {
                if (startNewGroup) {
                    startNewGroup = false
                    currentGroup = mutableListOf(line).also { result.add(it) }
                } else {
                    currentGroup.add(line)
                }
            }
            return this
        }
    }

    return lines.fold(Accumulator()) { acc, line -> acc.accumulate(line) }.result.toList()
}