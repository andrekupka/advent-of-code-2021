package de.andrekupka.adventofcode.day02

import com.github.h0tk3y.betterParse.combinators.and
import com.github.h0tk3y.betterParse.combinators.or
import com.github.h0tk3y.betterParse.combinators.use
import com.github.h0tk3y.betterParse.grammar.Grammar
import com.github.h0tk3y.betterParse.lexer.literalToken
import com.github.h0tk3y.betterParse.lexer.regexToken

val submarineCommandParser = object : Grammar<SubmarineCommand>() {
    val forward by literalToken("forward")
    val down by literalToken("down")
    val up by literalToken("up")

    val positiveInteger by regexToken("[1-9][0-9]*")

    val whiteSpace by regexToken("\\s+", ignore = true)

    val forwardParser by forward use { SubmarineMovement.FORWARD }
    val downParser by down use { SubmarineMovement.DOWN }
    val upParser by up use { SubmarineMovement.UP }

    val movementParser by forwardParser or downParser or upParser

    val positiveIntegerParser by positiveInteger use { text.toInt() }

    val commandParser by movementParser and positiveIntegerParser use { SubmarineCommand(t1, t2) }

    override val rootParser get() = commandParser
}
