package de.andrekupka.adventofcode.day02

enum class SubmarineMovement {
    FORWARD,
    DOWN,
    UP,
}

data class SubmarineCommand(
    val movement: SubmarineMovement,
    val amount: Int,
)

data class SubmarineLocation(
    val horizontalPosition: Int = 0,
    val depth: Int = 0,
)

data class SubmarineLocationWithAim(
    val horizontalPosition: Int = 0,
    val depth: Int = 0,
    val aim: Int = 0,
)

