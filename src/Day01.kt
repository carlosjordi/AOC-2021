fun main() {
    fun part1(input: List<String>): Int {
        val depths = input.map { it.toInt() }
        var result = 0

        for ((index, depth) in depths.withIndex()) {
            if (index == depths.size - 1) break
            if (depths[index + 1] > depth) {
                result++
            }
        }
        return result
    }

    fun part2(input: List<String>): Int {
        return input.size
    }

    val input = readInput("Day01")
    println(part1(input))
    println(part2(input))
}
