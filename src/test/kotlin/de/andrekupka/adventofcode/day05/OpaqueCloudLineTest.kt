package de.andrekupka.adventofcode.day05

import assertk.assertThat
import assertk.assertions.isEqualTo
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

internal class OpaqueCloudLineTest {

    @Test
    internal fun `should compute correct points for horizontal lines`() {
        val actualPoints = OpaqueCloudLine(Point(1, 1), Point(1, 3)).points

        assertThat(actualPoints).isEqualTo(listOf(Point(1, 1), Point(1, 2), Point(1, 3)))
    }

    @Test
    internal fun `should compute correct points for vertical lines`() {
        val actualPoints = OpaqueCloudLine(Point(9, 7), Point(7 ,7)).points

        assertThat(actualPoints).isEqualTo(listOf(Point(9, 7), Point(8, 7), Point(7, 7)))
    }
}