package de.andrekupka.adventofcode.day04

import de.andrekupka.adventofcode.utils.cross
import kotlin.math.sqrt


class BingoBoard(
    cells: List<Int>,
) {

    private val size = sqrt(cells.size.toDouble()).toInt()

    init {
        require(cells.isNotEmpty()) { "cells must not be empty" }
        require(size * size == cells.size) { "number of cells must be square number" }
    }

    private val cells = cells.map { Cell(it) }

    val indices by lazy {
        (0 until size) cross (0 until size)
    }

    fun mark(number: Int) {
        cells.withNumber(number).forEach { it.mark() }
    }

    fun isMarked(number: Int) =
        cells.withNumber(number).all { it.marked }

    private fun List<Cell>.withNumber(number: Int) = filter { it.value == number }

    operator fun get(rowIndex: Int, columnIndex: Int): Cell {
        require(rowIndex in 0 until size)
        require(columnIndex in 0 until size)

        return cells[rowIndex * size + columnIndex]
    }

    operator fun get(index: Pair<Int, Int>) = get(index.first, index.second)

    override fun equals(other: Any?) =
        other is BingoBoard && cells == other.cells

    override fun hashCode() = cells.hashCode()

    override fun toString() = cells
        .windowed(size = size, step = size)
        .joinToString(separator = System.lineSeparator()) { line ->
            line.joinToString(" ") { cell ->
                cell.toString()
            }
        }

    data class Cell(val value: Int) {

        var marked: Boolean = false
            private set

        fun mark() {
            this.marked = true
        }

        override fun toString(): String {
            val marker = if (marked) "+" else " "
            return "$marker$value".padStart(3, ' ')
        }
    }

    companion object {
        fun fromLines(lines: List<String>): BingoBoard {
            val numbers = lines.map { it.split(" ") }.flatten().map { it.toInt() }
            return BingoBoard(numbers)
        }
    }
}
