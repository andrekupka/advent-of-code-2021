package de.andrekupka.adventofcode.day02


fun SubmarineCommand.execute(location: SubmarineLocation) = location.run {
    when(movement) {
        SubmarineMovement.FORWARD -> copy(horizontalPosition = horizontalPosition + amount)
        SubmarineMovement.DOWN -> copy(depth = depth + amount)
        SubmarineMovement.UP -> copy(depth = depth - amount)
    }
}

fun SubmarineCommand.execute(location: SubmarineLocationWithAim) = location.run {
    when(movement) {
        SubmarineMovement.FORWARD -> copy(horizontalPosition = horizontalPosition + amount, depth = depth + aim * amount)
        SubmarineMovement.DOWN -> copy(aim = aim + amount)
        SubmarineMovement.UP -> copy(aim = aim - amount)
    }
}