package de.andrekupka.adventofcode.day06

class LanternFishPopulation(private val population: Map<Int, Long>) {

    val size get() = population.values.sum()

    @OptIn(ExperimentalStdlibApi::class)
    fun reproduce(): LanternFishPopulation {
        val newPopulation = buildMap<Int, Long> {
            population.forEach { (timer, count) ->
                if (timer == 0) {
                    compute(6) { _, currentCount -> (currentCount ?: 0) + count }
                    put(8, count)
                } else {
                    compute(timer - 1) { _, currentCount -> (currentCount ?: 0) + count }
                }
            }
        }
        return LanternFishPopulation(newPopulation)
    }
}