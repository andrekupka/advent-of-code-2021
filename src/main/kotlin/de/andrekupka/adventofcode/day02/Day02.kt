package de.andrekupka.adventofcode.day02

import com.github.h0tk3y.betterParse.grammar.parseToEnd
import de.andrekupka.adventofcode.utils.readResourceLinesNotBlank


fun main() {
    val commandLines = readResourceLinesNotBlank("/day02/input")

    val commands = commandLines.map { submarineCommandParser.parseToEnd(it) }

    val location = commands.fold(SubmarineLocation()) { previousLocation, command -> command.execute(previousLocation) }

    println("Final location is $location")

    val result = location.run { depth * horizontalPosition }
    println("Depth multiplied by horizontal position is $result")

    val locationWithAim = commands.fold(SubmarineLocationWithAim()) { previousLocation, command ->
        command.execute(previousLocation)
    }

    println("Final location with aim is $locationWithAim")

    val advancedResult = locationWithAim.run { depth * horizontalPosition }
    println("Depth multiplied by horizontal position with advanced movement is $advancedResult")
}