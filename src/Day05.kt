import kotlin.math.abs

fun main() {
    fun part1(input: List<String>): Int {
        val map = mutableMapOf<Map<Int, Int>, Int>()
        input.map { pair ->
            val coordinates = Coordinates()
            val numbers = pair.split(" -> ")
            numbers[0].split(",").apply {
                coordinates.x1 = this[0].toInt()
                coordinates.y1 = this[1].toInt()
            }
            numbers[1].split(",").apply {
                coordinates.x2 = this[0].toInt()
                coordinates.y2 = this[1].toInt()
            }
            coordinates.apply {
                if (x1 == x2 || y1 == y2) {
                    if (x1 == x2) {
                        val min = minOf(y1, y2)
                        val max = maxOf(y1, y2)
                        for (y in min..max) {
                            if (map.containsKey(mapOf(x1 to y))) {
                                map[mapOf(x1 to y)] = 1 + map[mapOf(x1 to y)]!!
                            } else {
                                map[mapOf(x1 to y)] = 1
                            }
                        }
                    } else if (y1 == y2) {
                        val min = minOf(x1, x2)
                        val max = maxOf(x1, x2)
                        for (x in min..max) {
                            if (map.containsKey(mapOf(x to y1))) {
                                map[mapOf(x to y1)] = 1 + map[mapOf(x to y1)]!!
                            } else {
                                map[mapOf(x to y1)] = 1
                            }
                        }
                    }
                }
            }
        }
        return map.count {
            it.value >= 2
        }
    }

    fun part2(input: List<String>): Int {
        val map = mutableMapOf<Map<Int, Int>, Int>()
        input.map { pair ->
            val coordinates = Coordinates()
            val numbers = pair.split(" -> ")
            numbers[0].split(",").apply {
                coordinates.x1 = this[0].toInt()
                coordinates.y1 = this[1].toInt()
            }
            numbers[1].split(",").apply {
                coordinates.x2 = this[0].toInt()
                coordinates.y2 = this[1].toInt()
            }
            coordinates.apply {
                if (x1 == x2 || y1 == y2) {
                    if (x1 == x2) {
                        val min = minOf(y1, y2)
                        val max = maxOf(y1, y2)
                        for (y in min..max) {
                            if (map.containsKey(mapOf(x1 to y))) {
                                map[mapOf(x1 to y)] = 1 + map[mapOf(x1 to y)]!!
                            } else {
                                map[mapOf(x1 to y)] = 1
                            }
                        }
                    } else if (y1 == y2) {
                        val min = minOf(x1, x2)
                        val max = maxOf(x1, x2)
                        for (x in min..max) {
                            if (map.containsKey(mapOf(x to y1))) {
                                map[mapOf(x to y1)] = 1 + map[mapOf(x to y1)]!!
                            } else {
                                map[mapOf(x to y1)] = 1
                            }
                        }
                    }
                } else {
                    val isX1Greater = x1 > x2
                    val isY1Greater = y1 > y2
                    val range = abs(x1 - x2) + 1
                    repeat(range) {
                        when {
                            isX1Greater && isY1Greater -> {
                                if (map.containsKey(mapOf((x2 + it) to (y2 + it)))) {
                                    map[mapOf((x2 + it) to (y2 + it))] = 1 + map[mapOf((x2 + it) to (y2 + it))]!!
                                } else {
                                    map[mapOf((x2 + it) to (y2 + it))] = 1
                                }
                            }
                            isX1Greater && !isY1Greater -> {
                                if (map.containsKey(mapOf((x2 + it) to (y2 - it)))) {
                                    map[mapOf((x2 + it) to (y2 - it))] = 1 + map[mapOf((x2 + it) to (y2 - it))]!!
                                } else {
                                    map[mapOf((x2 + it) to (y2 - it))] = 1
                                }
                            }
                            !isX1Greater && isY1Greater -> {
                                if (map.containsKey(mapOf((x2 - it) to (y2 + it)))) {
                                    map[mapOf((x2 - it) to (y2 + it))] = 1 + map[mapOf((x2 - it) to (y2 + it))]!!
                                } else {
                                    map[mapOf((x2 - it) to (y2 + it))] = 1
                                }
                            }
                            !isX1Greater && !isY1Greater -> {
                                if (map.containsKey(mapOf((x1 + it) to (y1 + it)))) {
                                    map[mapOf((x1 + it) to (y1 + it))] = 1 + map[mapOf((x1 + it) to (y1 + it))]!!
                                } else {
                                    map[mapOf((x1 + it) to (y1 + it))] = 1
                                }
                            }
                        }
                    }
                }
            }
        }
        return map.count {
            it.value >= 2
        }
    }

    val input = readInput("Day05")
    println(part1(input))
    println(part2(input))
}

data class Coordinates(
    var x1: Int = -1,
    var y1: Int = -1,
    var x2: Int = -1,
    var y2: Int = -1,
)