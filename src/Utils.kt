import java.io.File
import java.math.BigInteger
import java.security.MessageDigest
import java.util.*

/**
 * Reads lines from the given input txt file.
 */
fun readInput(name: String) = File("src", "$name.txt").readLines()

/**
 * Reads first line og bingo, so we have the numbers that are drawn
 * Used for Day04
 */
fun readFirstLineAsIntList(input: List<String>): List<Int> = input[0].split(",").map(String::toInt)

/**
 * Gets all bingo boards from input.
 * It algo gives another board that it's full of false values.
 * That it's to toggle if it has been marked or not
 * Used for Day04
 */
fun getBingoBoards(input: List<String>): List<Pair<Array<IntArray>, Array<BooleanArray>>> {
    val bingoBoards = mutableListOf<Pair<Array<IntArray>, Array<BooleanArray>>>()
    // this means we reached the last row of current bingo, so we add it to the list
    var lastBingoRowIndex = 6
    // blank line, so we have to skip it
    var blankLineIndex = 7
    // current row index in current bingo
    var bingoRowIndex = 0
    // current bingo
    var currentBingo = Array(5) { IntArray(5) }
    for (lineIndex in 2 until input.size) {
        if (lineIndex == blankLineIndex) {
            blankLineIndex += 6
            continue
        }
        val row = input[lineIndex].split(" ").filter { it.isNotEmpty() }.map { it.toInt() }
        currentBingo[bingoRowIndex] = intArrayOf(row[0], row[1], row[2], row[3], row[4])
        bingoRowIndex += 1

        if (lastBingoRowIndex == lineIndex) {
            bingoBoards.add(currentBingo to Array(5) { BooleanArray(5) })
            currentBingo = Array(5) { IntArray(5) }
            lastBingoRowIndex += 6
            bingoRowIndex = 0
        }
    }
    return bingoBoards
}

fun checkNumberInBingo(drawnNumber: Int, bingoBoards: List<Pair<Array<IntArray>, Array<BooleanArray>>>) {
    for (pair in bingoBoards) {
        for (row in 0 until 5) {
            for (column in 0 until 5) {
                if (pair.first[row][column] == drawnNumber) {
                    pair.second.markNumberInBingo(row, column)
                    break
                }
            }
        }
    }
}

fun Array<BooleanArray>.markNumberInBingo(row: Int, column: Int) {
    this[row][column] = true
}

fun Array<BooleanArray>.checkRowOrColumnCompleted(): Boolean {
    for (row in 0 until 5) {
        var columnsMarked = 0
        for (column in 0 until 5) {
            if (this[row][column]) {
                columnsMarked++
                // means whole current row is marked
                if (columnsMarked == 5) {
                    return true
                }
            }
        }
    }
    for (column in 0 until 5) {
        var rowsMarked = 0
        for (row in 0 until 5) {
            if (this[row][column]) {
                rowsMarked++
                // means whole current column is marked
                if (rowsMarked == 5) {
                    return true
                }
            }
        }
    }
    return false
}

fun Pair<Array<IntArray>, Array<BooleanArray>>.sumNotMarkedNumbers(): Int {
    var sum = 0
    for (row in 0 until 5) {
        for (column in 0 until 5) {
            if (!second[row][column]) {
                sum += first[row][column]
            }
        }
    }
    return sum
}

/**
 * Converts string to md5 hash.
 */
fun String.md5(): String = BigInteger(1, MessageDigest.getInstance("MD5").digest(toByteArray())).toString(16)

/**
 * Gets the value from the command line, so we know how much the submarine is moving
 */
fun String.movementValue(): Int = substringAfter(DELIMITER).toInt()

// Day06

fun getLanternFishDaysLeft(input: List<String>): List<Int> {
    return input[0].split(",").map(String::toInt)
}

/**
 * Goes forward the amount of days provided in [days] param
 *
 * Returns the amount of lanternfish after those days
 */
fun List<Int>.advanceDays(days: Int): Long {
    // ages of fish, all start at 0
    val ages = (0..8).associateWith { 0L }.toMutableMap()
    // filling with amount of each lanternFish
    forEach { age ->
        ages[age] = ages[age]!! + 1
    }
    // simulating days going on
    for (d in 0 until days) {
        val prevAge0 = ages[0]!!
        ages[0] = ages[1]!!
        ages[1] = ages[2]!!
        ages[2] = ages[3]!!
        ages[3] = ages[4]!!
        ages[4] = ages[5]!!
        ages[5] = ages[6]!!
        ages[6] = ages[7]!! + prevAge0
        ages[7] = ages[8]!!
        ages[8] = prevAge0
    }
    return ages.values.sum()
}