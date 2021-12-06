package de.andrekupka.adventofcode.day04

import assertk.assertThat
import assertk.assertions.isFailure
import assertk.assertions.isInstanceOf
import assertk.assertions.isSuccess
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test

class BingoBoardTest {

    @Nested
    inner class Constructor {

        @Test
        fun `should accept a square number of cells`() {
            assertThat {
                BingoBoard(listOf(1, 2, 3, 4))
            }.isSuccess()
        }

        @Test
        internal fun `should reject empty list of cells`() {
            assertThat {
                BingoBoard(emptyList())
            }.isFailure().isInstanceOf(IllegalArgumentException::class)
        }

        @Test
        fun `should reject a non square number of cells`() {
            assertThat {
                BingoBoard(listOf(1, 2, 3))
            }.isFailure().isInstanceOf(IllegalArgumentException::class)
        }
    }
}