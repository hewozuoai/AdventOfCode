import kotlin.math.abs

fun main() {
    val day = "Day13"

    val lines = getLines("$day-input")
    println("$day-Part2 answer: ")
    doChallenge(lines)
}

private const val FOLD_INSTRUCTION = "fold"

private fun doChallenge(content: List<String>) {
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
    }

    val (x, y) = grid.maxOf { it.first } to grid.maxOf { it.second }
    println((0..y).joinToString("\n") { i -> (0..x).joinToString("") { j -> if (j to i in grid) "#" else " "} })
}
