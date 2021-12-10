fun main() {

    val test = listOf(
        "2199943210",
        "3987894921",
        "9856789892",
        "8767896789",
        "9899965678"
    )
    val testAnswer = doChallenge(test)
    println("testAnswer: $testAnswer")
    assert(testAnswer == 15)

    val lines = getLines("Day09-input")
    val answer = doChallenge(lines)
    println("Day9-Part1 answer: $answer")
}

private fun doChallenge(content: List<String>): Int {

    var answer = 0
    val array = content.map { it.map { num -> num.digitToInt() } }

    array.forEachIndexed { i, line ->
        line.forEachIndexed { j, num ->
            if (foundLowPoint(i, array, j, num, line))  answer += (num + 1)
        }
    }

    return answer
}
