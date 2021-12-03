package de.andrekupka.adventofcode.day03

class SubmarineDiagnosticReport(
    private val numbers: List<Int>,
    private val bitSize: Int,
) {

    init {
        require(bitSize in 0..Int.SIZE_BITS)
    }

    val powerConsumption by lazy {
        computePowerConsumption()
    }

    val lifeSupportRating by lazy {
        computeLifeSupportRating()
    }

    private fun Int.bitAtIndex(index: Int): Boolean {
        require(index in (0 until Int.SIZE_BITS)) { "index must be in range 0 until Int.SIZE_BITS"}
        return this and (1 shl index) > 0
    }

    private fun Boolean.toIntFromBit() = if (this) 1 else 0

    private fun List<Boolean>.toIntFromBinary() = foldIndexed(0) { index, number, bit ->
        number or (bit.toIntFromBit() shl index)
    }

    private fun Int.invertWithBitSize(bitSize: Int): Int {
        require(bitSize in (0..Int.SIZE_BITS)) { "index must be in range 0 until Int.SIZE_BITS"}
        val mask = (1 shl bitSize) - 1
        return this.inv() and mask
    }

    private fun computePowerConsumption(): Int {
        val bitsOfIndices = (0 until bitSize).map { bitIndex ->
            numbers.map { it.bitAtIndex(bitIndex) }
        }

        val mostCommonBits = bitsOfIndices.map { bits ->
            val count = bits.count { it }
            count >= bits.size - count
        }

        val gammaRate = mostCommonBits.toIntFromBinary()
        val epsilonRate = gammaRate.invertWithBitSize(bitSize)

        return gammaRate * epsilonRate
    }

    private fun List<Int>.mostCommonBitAtIndex(index: Int) =
        map { it.bitAtIndex(index) }.count { it }.let { it >= size - it }

    private fun List<Int>.leastCommonBitAtIndex(index: Int) =
        !mostCommonBitAtIndex(index)

    private fun List<Int>.filterByIndexedBits(bitSize: Int, bitSelector: (List<Int>, Int) -> Boolean) =
        (bitSize-1 downTo  0).fold(this) { currentNumbers, bitIndex ->
            if (currentNumbers.size == 1) {
                currentNumbers
            } else {
                val bit = bitSelector(currentNumbers, bitIndex)
                currentNumbers.filter { it.bitAtIndex(bitIndex) == bit }
            }
        }.single()


    private fun computeLifeSupportRating(): Int {
        val oxygenGeneratorRating = numbers.filterByIndexedBits(bitSize) { currentNumbers, bitIndex ->
            currentNumbers.mostCommonBitAtIndex(bitIndex)
        }

        val co2ScrubberRating = numbers.filterByIndexedBits(bitSize) { currentNumbers, bitIndex ->
            currentNumbers.leastCommonBitAtIndex(bitIndex)
        }

        return oxygenGeneratorRating * co2ScrubberRating
    }

    companion object {
        fun fromString(input: String) = fromLines(input.lines())

        fun fromLines(lines: List<String>): SubmarineDiagnosticReport {
            val bitSize = lines.map { it.length }.distinct().single()
            val numbers = lines.map { it.toInt(radix = 2) }
            return SubmarineDiagnosticReport(numbers, bitSize)
        }
    }
}