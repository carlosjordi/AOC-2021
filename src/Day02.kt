fun main() {
    fun part1(input: List<String>): Int {
        val position = Position()
        input.forEach { command ->
            when (command.substringBefore(DELIMITER)) {
                Commands.FORWARD.command -> {
                    position.horizontal += command.movementValue()
                }
                Commands.UP.command -> {
                    position.depth -= command.movementValue()
                }
                Commands.DOWN.command -> {
                    position.depth += command.movementValue()
                }
            }
        }
        return position.horizontal * position.depth
    }

    fun part2(input: List<String>): Int {
        val position = Position()
        input.forEach { command ->
            when (command.substringBefore(DELIMITER)) {
                Commands.FORWARD.command -> {
                    position.horizontal += command.movementValue()
                    position.depth += command.movementValue() * position.aim
                }
                Commands.UP.command -> {
                    position.aim -= command.movementValue()
                }
                Commands.DOWN.command -> {
                    position.aim += command.movementValue()
                }
            }
        }
        return position.horizontal * position.depth
    }


    val input = readInput("Day02")
    println(part1(input))
    println(part2(input))
}

const val DELIMITER = " "

data class Position(var horizontal: Int = 0, var depth: Int = 0, var aim: Int = 0)

enum class Commands(val command: String) {
    FORWARD("forward"),
    UP("up"),
    DOWN("down")
}