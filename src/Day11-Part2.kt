fun main() {
    val day = "Day11"
    val test = listOf(
        "5483143223",
        "2745854711",
        "5264556173",
        "6141336146",
        "6357385478",
        "4167524645",
        "2176841721",
        "6882881134",
        "4846848554",
        "5283751526"
    )
    val testAnswer = doChallenge(test)
    println("testAnswer: $testAnswer")
    assert(testAnswer == 195)

    val lines = getLines("$day-input")
    val answer = doChallenge(lines)
    println("$day-Part2 answer: $answer")
}

private var answer = 0

private fun doChallenge(content: List<String>): Int {
    val map = createGrid(9, 9)
    val array = Array(10) { x -> Array(10) { y -> content[x][y].digitToInt() } }

    repeat(Int.MAX_VALUE) {
        map.forEach { (x, y) -> array[x][y]++ }
        map.forEach { (x, y) -> if (array[x][y] == READY_FOR_FLASH) flash(x, y, array) }
        if (array.flatten().all { i -> i == FLASHED }) {
            return it + 1
        }
        map.forEach { (x, y) -> if (array[x][y] == FLASHED) array[x][y] = 0 }
    }

    return answer
}
