package de.andrekupka.adventofcode.day03

import de.andrekupka.adventofcode.utils.readResourceLinesNotBlank

fun main() {
    val reportStrings = readResourceLinesNotBlank("/day03/input")

    val report = SubmarineDiagnosticReport.fromLines(reportStrings)

    println("power consumption is ${report.powerConsumption}")
    println("life support rating is ${report.lifeSupportRating}")
}
