import java.lang.Integer.max

var SIZE = 12

fun main() {

    val lines = getLines("Day03-input")
    val common = getMostCommon(lines)
    val least = getLeastCommon(lines)

    val answer = convertBinaryToDecimal(common) * convertBinaryToDecimal(least)
    println("Day3-Part2 answer: $answer")
}

private fun getMostCommon(
    lines: List<String>
): String {
    var zero = 0
    var one = 0
    var newLines = lines
    for (i in 0..SIZE) {

        if (i > 0) {
            val filter = if (zero > one) '0' else '1'
            newLines = newLines.filter { it[max(0, i - 1)] == filter }
            zero = 0
            one = 0
        }

        if (i == SIZE || newLines.count() == 1) break

        for (line in newLines) {
            val value = line[i]
            if (value == '0') {
                zero += 1
            } else {
                one += 1
            }
        }
    }
    return newLines.first()
}

private fun getLeastCommon(
    lines: List<String>
): String {
    var zero = 0
    var one = 0
    var newLines = lines
    for (i in 0..SIZE) {

        if (i > 0) {
            val filter = if (one < zero) '1' else '0'
            newLines = newLines.filter { it[max(0, i - 1)] == filter }
            zero = 0
            one = 0
        }

        if (shouldBreak(i, newLines.count())) break

        for (line in newLines) {
            val value = line[i]
            if (value == '0') {
                zero += 1
            } else {
                one += 1
            }
        }
    }
    return newLines.first()
}

private fun shouldBreak(i: Int, listCount: Int) =
    i == SIZE || listCount == 1