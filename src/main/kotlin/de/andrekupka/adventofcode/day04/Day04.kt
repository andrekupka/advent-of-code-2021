package de.andrekupka.adventofcode.day04

import de.andrekupka.adventofcode.utils.groupByBlankLines
import de.andrekupka.adventofcode.utils.head
import de.andrekupka.adventofcode.utils.readResourceLines
import de.andrekupka.adventofcode.utils.tail

fun main() {
    val lines = readResourceLines("/day04/input")

    val lineGroups = groupByBlankLines(lines)

    val inputNumbers = lineGroups.head().single().split(" ").map { it.toInt() }
    val boardInputLines = lineGroups.tail()

    val boards = boardInputLines.map { BingoBoard.fromLines(it) }
}