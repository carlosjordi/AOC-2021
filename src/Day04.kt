fun main() {
    fun part1(input: List<String>): Int {
        val drawnNumbers = readFirstLineAsIntList(input)
        val bingoBoards = getBingoBoards(input)

        var winnerBoard: Array<IntArray>? = null
        var winnerMarkedBoard: Array<BooleanArray>? = null
        var winnerNumber = 0
        for (number in drawnNumbers) {
            checkNumberInBingo(number, bingoBoards)
            for (board in bingoBoards) {
                if (board.second.checkRowOrColumnCompleted()) {
                    winnerBoard = board.first
                    winnerMarkedBoard = board.second
                    winnerNumber = number
                    break
                }
            }
            if (winnerBoard != null) break
        }
        var sum = 0
        if (winnerBoard != null && winnerMarkedBoard != null) {
            sum = (winnerBoard to winnerMarkedBoard).sumNotMarkedNumbers()
        }

        return sum * winnerNumber
    }

    fun part2(input: List<String>): Int {
        return input.size
    }

    val input = readInput("Day04")
    println(part1(input))
    println(part2(input))
}