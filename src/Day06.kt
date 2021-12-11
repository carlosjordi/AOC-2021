fun main() {
    fun part1(input: List<String>): Long {
        val daysLeft = getLanternFishDaysLeft(input)

        return daysLeft.advanceDays(80)
    }

    fun part2(input: List<String>): Long {
        val daysLeft = getLanternFishDaysLeft(input)

        return daysLeft.advanceDays(256)
    }

    val input = readInput("Day06")
    println(part1(input))
    println(part2(input))
}