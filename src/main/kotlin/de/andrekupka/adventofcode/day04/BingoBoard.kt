package de.andrekupka.adventofcode.day04

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

    override fun equals(other: Any?) =
        other is BingoBoard && cells == other.cells

    override fun hashCode() = cells.hashCode()

    override fun toString() = cells
        .windowed(size = size, step = size)
        .joinToString(separator = System.lineSeparator()) { line ->
            line.joinToString(" ") { cell ->
                cell.toString().padStart(2, ' ')
            }
        }

    data class Cell(val value: Int) {

        var marked: Boolean = false
            private set

        fun mark() {
            this.marked = true
        }
    }

    companion object {
        fun fromLines(lines: List<String>): BingoBoard {
            val numbers = lines.map { it.split(" ") }.flatten().map { it.toInt() }
            return BingoBoard(numbers)
        }
    }
}
