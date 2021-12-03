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

fun determinePowerConsumption(report: List<Int>, bitSize: Int) {
    val bitsOfIndices = (0 until bitSize).map { bitIndex ->
        report.map { it.bitAtIndex(bitIndex) }
    }

    val mostCommonBits = bitsOfIndices.map { bits -> bits.count { it } > bits.size / 2 }

    val gammaRate = mostCommonBits.toIntFromBinary()
    val epsilonRate = gammaRate.invertWithBitSize(bitSize)

    val powerConsumption = gammaRate * epsilonRate

    println("gamma rate is $gammaRate")
    println("epsilon rate is $epsilonRate")
    println("power consumption is $powerConsumption")
}

fun List<Int>.mostCommonBitAtIndex(index: Int): Boolean {
    val onesCount = map { it.bitAtIndex(index) }.count { it }
    return onesCount >= (size - onesCount)
}

fun List<Int>.filterByIndexedBits(bitSize: Int, bitSelector: (List<Int>, Int) -> Boolean) =
    (bitSize-1 downTo  0).fold(this) { currentNumbers, bitIndex ->
        println(currentNumbers.map { it.toString(radix=2) })
        if (currentNumbers.size == 1) {
            currentNumbers
        } else {
            val bit = bitSelector(currentNumbers, bitIndex)
            currentNumbers.filter { it.bitAtIndex(bitIndex) == bit }
        }
    }.single()


fun determineLifeSupportRating(report: List<Int>, bitSize: Int) {
    val oxygenGeneratorRating = report.filterByIndexedBits(bitSize) { currentNumbers, bitIndex ->
        currentNumbers.mostCommonBitAtIndex(bitIndex)
    }

    val co2ScrubberRating = report.filterByIndexedBits(bitSize) { currentNumbers, bitIndex ->
        !currentNumbers.mostCommonBitAtIndex(bitIndex)
    }

    val lifeSupportRating = oxygenGeneratorRating * co2ScrubberRating

    println("oxygen generator rating is $oxygenGeneratorRating")
    println("co2 scrubber rating is $co2ScrubberRating")
    println("life support rating is $lifeSupportRating")
}

fun main() {
    val reportStrings = readResourceLinesNotBlank("/day03/input")

    val bitSize = reportStrings.map { it.length }.distinct().single()

    val report = reportStrings.map { it.toInt(radix = 2) }

    determinePowerConsumption(report, bitSize)
    determineLifeSupportRating(report, bitSize)
}
