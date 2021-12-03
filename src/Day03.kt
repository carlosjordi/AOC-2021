import jdk.jfr.Threshold

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
        return input.size
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