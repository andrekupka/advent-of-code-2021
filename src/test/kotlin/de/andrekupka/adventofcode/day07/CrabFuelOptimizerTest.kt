package de.andrekupka.adventofcode.day07

import assertk.assertThat
import assertk.assertions.isEqualTo
import assertk.assertions.isFailure
import assertk.assertions.isInstanceOf
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test

internal class CrabFuelOptimizerTest {

    companion object {
        val INITIAL_POSITIONS = listOf(16, 1, 2, 0, 4, 2, 7, 1, 2, 14)
    }

    @Nested
    inner class DetermineLinearFuelUsage {

        @Test
        internal fun `should determine correct fuel usage with increasing step`() {
            val actualFuelUsage = determineLinearFuelUsage(10, 11)
            assertThat(actualFuelUsage).isEqualTo(1)
        }

        @Test
        internal fun `should determine correct fuel usage with decreasing step`() {
            val actualFuelUsage = determineLinearFuelUsage(11, 10)
            assertThat(actualFuelUsage).isEqualTo(1)
        }

        @Test
        internal fun `should determine correct fuel usage for even step count`() {
            val actualFuelUsage = determineLinearFuelUsage(10, 14)
            assertThat(actualFuelUsage).isEqualTo(10)
        }

        @Test
        internal fun `should determine correct fuel usage for odd step count`() {
            val actualFuelUsage = determineLinearFuelUsage(10, 15)
            assertThat(actualFuelUsage).isEqualTo(15)
        }
    }

    @Nested
    inner class OptimizeFuelUsage {

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

        @Test
        internal fun `should compute correct minimal fuel usage with linear usage`() {
            val positionAndFuel = optimizeLinearFuelUsage(INITIAL_POSITIONS)

            assertThat(positionAndFuel).isEqualTo(PositionWithFuel(5, 168))

        }
    }
}