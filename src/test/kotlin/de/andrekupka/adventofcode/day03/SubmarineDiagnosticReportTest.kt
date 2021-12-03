package de.andrekupka.adventofcode.day03

import assertk.assertThat
import assertk.assertions.isEqualTo
import org.junit.jupiter.api.Test

class SubmarineDiagnosticReportTest {

    companion object {
        private val TEST_REPORT = """
            00100
            11110
            10110
            10111
            10101
            01111
            00111
            11100
            10000
            11001
            00010
            01010
        """.trimIndent()
    }

    @Test
    internal fun `should compute correct power consumption`() {
        assertThat(SubmarineDiagnosticReport.fromString(TEST_REPORT).powerConsumption).isEqualTo(198)
    }

    @Test
    internal fun `should compute correct life support rating`() {
        assertThat(SubmarineDiagnosticReport.fromString(TEST_REPORT).lifeSupportRating).isEqualTo(230)
    }
}