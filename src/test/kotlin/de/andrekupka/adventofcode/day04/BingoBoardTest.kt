package de.andrekupka.adventofcode.day04

import assertk.assertThat
import assertk.assertions.isEqualTo
import assertk.assertions.isFailure
import assertk.assertions.isInstanceOf
import assertk.assertions.isSuccess
import org.junit.jupiter.api.Test

class BingoBoardTest {

    @Test
    internal fun `constructor should accept a square number of cells`() {
        assertThat {
            BingoBoard(listOf(1, 2, 3, 4))
        }.isSuccess()
    }

    @Test
    internal fun `constructor should reject empty list of cells`() {
        assertThat {
            BingoBoard(emptyList())
        }.isFailure().isInstanceOf(IllegalArgumentException::class)
    }

    @Test
    internal fun `constructor should reject a non square number of cells`() {
        assertThat {
            BingoBoard(listOf(1, 2, 3))
        }.isFailure().isInstanceOf(IllegalArgumentException::class)
    }

    @Test
    internal fun `fromLines should parse bingo board with one element`() {
        val actualBoard = BingoBoard.fromLines(listOf("1"))

        assertThat(BingoBoard(listOf(1))).isEqualTo(actualBoard)
    }

    @Test
    internal fun `fromLines should parse bingo board with multiple elements`() {
        val actualBoard = BingoBoard.fromLines(listOf("1 3", "5 12"))

        assertThat(BingoBoard(listOf(1, 3, 5, 12))).isEqualTo(actualBoard)
    }
}