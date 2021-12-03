package de.andrekupka.adventofcode.day03

import de.andrekupka.adventofcode.utils.readResourceLinesNotBlank

fun Int.bitAtIndex(index: Int): Boolean {
    require(index in (0 until Int.SIZE_BITS)) { "index must be in range 0 until Int.SIZE_BITS"}
    return this and (1 shl index) > 0
}

fun Boolean.toIntFromBit() = if (this) 1 else 0

fun List<Boolean>.toIntFromBinary() = foldIndexed(0) { index, number, bit ->
    number or (bit.toIntFromBit() shl index)
}

fun Int.invertWithBitSize(bitSize: Int): Int {
    require(bitSize in (0..Int.SIZE_BITS)) { "index must be in range 0 until Int.SIZE_BITS"}
    val mask = (1 shl bitSize) - 1
    return this.inv() and mask
}

fun main() {
    val numberStrings = readResourceLinesNotBlank("/day03/input")

    val bitSize = numberStrings.map { it.length }.distinct().single()

    val numbers = numberStrings.map { it.toInt(radix = 2) }

    val bitsOfIndices = (0 until bitSize).map { bitIndex ->
        numbers.map { it.bitAtIndex(bitIndex) }
    }

    val mostCommonBits = bitsOfIndices.map { bits -> bits.count { it } > bits.size / 2 }

    val gammaRate = mostCommonBits.toIntFromBinary()
    val epsilonRate = gammaRate.invertWithBitSize(bitSize)

    val product = gammaRate * epsilonRate

    println("gamma rate is $gammaRate")
    println("epsilon rate is $epsilonRate")
    println("product of both is $product")
}