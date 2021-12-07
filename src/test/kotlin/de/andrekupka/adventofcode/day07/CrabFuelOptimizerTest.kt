package de.andrekupka.adventofcode.day07

import assertk.assertThat
import assertk.assertions.isEqualTo
import assertk.assertions.isFailure
import assertk.assertions.isInstanceOf
import org.junit.jupiter.api.Test

internal class CrabFuelOptimizerTest {

    companion object {
        val INITIAL_POSITIONS = listOf(16, 1, 2, 0, 4, 2, 7, 1, 2, 14)
    }

    @Test
    internal fun `should reject empty position list`() {
        assertThat {
            optimizeConstantFuelUsage(emptyList())
        }.isFailure().isInstanceOf(IllegalArgumentException::class)
    }

    @Test
    internal fun `should compute correct minimal fuel usage with constant usage`() {
        val positionAndFuel = optimizeConstantFuelUsage(INITIAL_POSITIONS)

        assertThat(positionAndFuel).isEqualTo(PositionWithFuel(2, 37))
    }
}