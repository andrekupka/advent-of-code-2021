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

    private data class Cell(val value: Int) {

        var marked: Boolean = false
            private set

        fun mark() {
            this.marked = true
        }
    }
}
