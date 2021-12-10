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
    assert(testAnswer == 1134)

    val lines = getLines("Day09-input")
    val answer = doChallenge(lines)
    println("Day9-Part2 answer: $answer")
}

val processed = mutableListOf<Pair<Int, Int>>()

private fun doChallenge(content: List<String>): Int {

    val array = content.map { it.map { num -> num.digitToInt() } }
    val sizes = mutableListOf<Int>()

    array.forEachIndexed { i, line ->
        line.forEachIndexed { j, num ->
            if (foundLowPoint(i, array, j, num, line)){
                processed.clear()
                getSizeFor(array, i, j)
                sizes.add(processed.size)
            }
        }
    }
    sizes.sortDescending()
    return sizes[0] * sizes[1] * sizes[2]
}

private fun getSizeFor(array: List<List<Int>>, i: Int, j: Int) {
    if (exitEarly(i, array, j)) return
    processed.add(Pair(i, j))
    getSizeFor(array, i+1, j)
    getSizeFor(array, i-1, j)
    getSizeFor(array, i, j+1)
    getSizeFor(array, i, j-1)
}

fun exitEarly(i: Int, array: List<List<Int>>, j: Int): Boolean {
    return (alreadyBeenHere(i, j) ||
            beginningOrEnd(i, array, j) ||
            hitMaximumNumber(array, i, j))
}

private fun hitMaximumNumber(
    array: List<List<Int>>,
    i: Int,
    j: Int
) = array[i][j] == 9

private fun alreadyBeenHere(i: Int, j: Int) = processed.contains(Pair(i, j))

private fun beginningOrEnd(
    i: Int,
    array: List<List<Int>>,
    j: Int
) = (i == array.size || i < 0 || j < 0 || j == array[i].size)


