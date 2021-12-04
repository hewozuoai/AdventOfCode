import java.io.File
import java.math.BigInteger
import java.security.MessageDigest

/**
 * Reads lines from the given input txt file.
 */
fun readInput(name: String) = File("src", "$name.txt").readLines()

/**
 * Converts string to md5 hash.
 */
fun String.md5(): String = BigInteger(1, MessageDigest.getInstance("MD5").digest(toByteArray())).toString(16)

fun getLinesAsInts(file: String): List<Int> {
    return readInput(file).map { it.toInt() }
}

fun getLines(file: String): List<String> {
    return readInput(file)
}

fun convertBinaryToDecimal(binary: String): Int {
    var num = binary.toLong()
    var decimalNumber = 0
    var i = 0
    var remainder: Long

    while (num.toInt() != 0) {
        remainder = num % 10
        num /= 10
        decimalNumber += (remainder * Math.pow(2.0, i.toDouble())).toInt()
        ++i
    }
    return decimalNumber
}

fun createBoards (lines: List<String>): List<Board> {
    val boards = arrayListOf<Board>()
    for (i in lines.indices step 5) {
        val board = Board(lines[i], lines[i+1], lines[i+2], lines[i+3],lines[i+4])
        boards.add(board)
    }
    return boards
}

class Board(l1: String, l2: String, l3: String, l4: String, l5: String) {
    var line1 = arrayOf<String>()
    var line2 = arrayOf<String>()
    var line3 = arrayOf<String>()
    var line4 = arrayOf<String>()
    var line5 = arrayOf<String>()

    init {
        line1 = l1.split(" ").filter { it.isNotEmpty() }.toTypedArray()
        line2 = l2.split(" ").filter { it.isNotEmpty() }.toTypedArray()
        line3 = l3.split(" ").filter { it.isNotEmpty() }.toTypedArray()
        line4 = l4.split(" ").filter { it.isNotEmpty() }.toTypedArray()
        line5 = l5.split(" ").filter { it.isNotEmpty() }.toTypedArray()
    }

    fun checkMarkNumber(num: String) {
        checkLine(line1, num)
        checkLine(line2, num)
        checkLine(line3, num)
        checkLine(line4, num)
        checkLine(line5, num)
    }

    private fun checkLine(lineArray: Array<String>, num: String) {
        if (lineArray.contains(num)) {
            lineArray[lineArray.indexOf(num)] = "*$num*"
        }
    }

    fun isWinner(): Boolean {
        return anyLineWinners() || anyColumnWinners()
    }

    private fun anyLineWinners() = line1.all { it.startsWith("*") && it.endsWith("*") } ||
            line2.all { it.startsWith("*") && it.endsWith("*") } ||
            line3.all { it.startsWith("*") && it.endsWith("*") } ||
            line4.all { it.startsWith("*") && it.endsWith("*") } ||
            line5.all { it.startsWith("*") && it.endsWith("*") }

    private fun anyColumnWinners(): Boolean {
        for (i in 0..4) {
            val testWin = arrayOf(line1[i], line2[i], line3[i], line4[i], line5[i]).all { it.startsWith("*") && it.endsWith("*") }
            if (testWin) return true
        }
        return false
    }

    fun sumUncalledNumbers(): Int =
        line1.filter { !it.startsWith("*") && !it.endsWith("*") }.map { it.toInt() }.sum() +
                line2.filter { !it.startsWith("*") && !it.endsWith("*") }.map { it.toInt() }.sum() +
                line3.filter { !it.startsWith("*") && !it.endsWith("*") }.map { it.toInt() }.sum() +
                line4.filter { !it.startsWith("*") && !it.endsWith("*") }.map { it.toInt() }.sum() +
                line5.filter { !it.startsWith("*") && !it.endsWith("*") }.map { it.toInt() }.sum()
}