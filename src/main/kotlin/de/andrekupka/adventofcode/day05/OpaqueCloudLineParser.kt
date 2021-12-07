package de.andrekupka.adventofcode.day05

import com.github.h0tk3y.betterParse.combinators.and
import com.github.h0tk3y.betterParse.combinators.skip
import com.github.h0tk3y.betterParse.combinators.use
import com.github.h0tk3y.betterParse.grammar.Grammar
import com.github.h0tk3y.betterParse.lexer.literalToken
import com.github.h0tk3y.betterParse.lexer.regexToken

val opaqueCloudLineParser = object : Grammar<OpaqueCloudLine>() {
    val arrow by literalToken("->")
    val number by regexToken("0|[1-9][0-9]*")
    val comma by literalToken(",")

    val whitespace by regexToken("\\s+", ignore = true)

    val numberParser by number use { text.toInt() }
    val pointParser by numberParser and skip(comma) and numberParser use { Point(t1, t2) }

    val opaqueCloudLineParser by pointParser and skip(arrow) and pointParser use { OpaqueCloudLine(t1, t2)}

    override val rootParser get() = opaqueCloudLineParser
}