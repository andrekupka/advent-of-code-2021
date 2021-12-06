package de.andrekupka.adventofcode.day04

import de.andrekupka.adventofcode.utils.groupByBlankLines
import de.andrekupka.adventofcode.utils.head
import de.andrekupka.adventofcode.utils.readResourceLines
import de.andrekupka.adventofcode.utils.tail

fun analyzeBoards(boards: List<BingoBoard>, analyzer: (List<BingoBoard>) -> Unit) {
    analyzer(boards)
    boards.forEach { it.reset() }
}

fun findFirstWinningBoard(boards: List<BingoBoard>, numbers: List<Int>): BingoBoard? {
    numbers.forEach { number ->
        boards.forEach { it.mark(number) }

        val winner = boards.firstOrNull { it.won }
        if (winner != null) {
            return winner
        }
    }

    return null
}

fun findLastWinningBoard(boards: List<BingoBoard>, numbers: List<Int>): BingoBoard? {
    return numbers.fold(boards) { currentBoards, number ->
        currentBoards.forEach { it.mark(number) }
        if (currentBoards.size == 1 && currentBoards.firstOrNull()?.won == true) {
            return currentBoards[0]
        } else {
            currentBoards.filter { !it.won }
        }
    }.firstOrNull()
}

fun main() {
    val lines = readResourceLines("/day04/input").map { it.trim() }

    val lineGroups = groupByBlankLines(lines)

    val inputNumbers = lineGroups.head().single().split(",").map { it.toInt() }
    val boardInputLines = lineGroups.tail()

    val boards = boardInputLines.map { BingoBoard.fromLines(it) }

    analyzeBoards(boards) {
        val firstWinner = findFirstWinningBoard(it, inputNumbers)

        if (firstWinner == null) {
            println("There is no first winner.")
        } else {
            println("First winning board score is ${firstWinner.score}")
        }
    }

    analyzeBoards(boards) {
        val lastWinner = findLastWinningBoard(it, inputNumbers)

        if (lastWinner == null) {
            println("There is no last winner.")
        } else {
            println("Last winning board score is ${lastWinner.score}")
        }
    }
}