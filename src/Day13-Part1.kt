import kotlin.math.abs

fun main() {
    val day = "Day13"
    val test1 = listOf(
        "6,10",
        "0,14",
        "9,10",
        "0,3",
        "10,4",
        "4,11",
        "6,0",
        "6,12",
        "4,1",
        "0,13",
        "10,12",
        "3,4",
        "3,0",
        "8,4",
        "1,10",
        "2,14",
        "8,10",
        "9,0",
        "",
        "fold along y=7",
        "fold along x=5"
    )
    val testAnswer1 = doChallenge(test1)
    println("testAnswer1: $testAnswer1")
    assert(testAnswer1 == 17)

    val lines = getLines("$day-input")
    val answer = doChallenge(lines)
    println("$day-Part1 answer: $answer")
}

private const val FOLD_INSTRUCTION = "fold"

private fun doChallenge(content: List<String>): Int {
    var answer = 0
    val coordinates = content.filter { !it.startsWith(FOLD_INSTRUCTION) && it.isNotEmpty() }.map { it.split(",").map { c -> c.toInt() } }
    val folds = content.filter { it.startsWith(FOLD_INSTRUCTION) }.map { it.split(" ")[2].split("=") }.map { it[0] to it[1].toInt() }.toSet()
    var grid = coordinates.map { it[0] to it[1] }.toSet()

    folds.forEachIndexed { index, (direction, value) ->
        grid = grid.map { (x, y) ->
            if (direction == "x") {
                value - abs(x - value) to y
            } else {
                x to value - abs(y - value)
            }
        }.toSet()
        // first fold only
        if (index == 0) {
            answer = grid.size
        }
    }

    return answer
}
