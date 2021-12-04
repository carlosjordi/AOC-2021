fun main() {
    fun part1(input: List<String>): Int {
        val diagnostic = BinaryDiagnostic()
        input.forEach { binary ->
            binary.forEachIndexed { index, digit ->
                if (digit == '1') diagnostic.output[index] += 1
            }
        }
        val gammaRate = diagnostic.toGammaRate(input.size / 2)
        val epsilonRate = diagnostic.toEpsilonRate(input.size / 2)

        return gammaRate * epsilonRate
    }

    fun part2(input: List<String>): Int {
        var reducedOxygen = input
        // oxygen
        for (i in 0..11) {
            reducedOxygen = reducedOxygen.reduceList(i)
            if (reducedOxygen.size == 1) break
        }
        val oxygen = reducedOxygen[0].toInt(2)

        var reducedCo2 = input
        // co2
        for (i in 0..11) {
            reducedCo2 = reducedCo2.reduceList(i, false)
            if (reducedCo2.size == 1) break
        }
        val co2 = reducedCo2[0].toInt(2)

        return oxygen * co2
    }

    val input = readInput("Day03")
    println(part1(input))
    println(part2(input))
}

data class BinaryDiagnostic(val output: MutableList<Int> = mutableListOf(0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0)) {
    fun toGammaRate(threshold: Int): Int {
        return output.map { amount ->
            if (amount > threshold) 1 else 0
        }.joinToString(separator = "").toInt(2)
    }

    fun toEpsilonRate(threshold: Int): Int {
        return output.map { amount ->
            if (amount > threshold) 0 else 1
        }.joinToString(separator = "").toInt(2)
    }
}

private fun List<String>.reduceList(index: Int, isOxygenRating: Boolean = true): List<String> {
    if (isOxygenRating) {
        val ones = count {
            it[index] == '1'
        }
        return filter {
            if (ones >= size / 2.0) {
                it[index] == '1'
            } else {
                it[index] == '0'
            }
        }
    } else {
        val zeros = count {
            it[index] == '0'
        }
        return filter {
            if (zeros <= size / 2.0) {
                it[index] == '0'
            } else {
                it[index] == '1'
            }
        }
    }
}