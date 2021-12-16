fun main() {
    val day = "Day15"
    val test1 = listOf(
        "1163751742",
        "1381373672",
        "2136511328",
        "3694931569",
        "7463417111",
        "1319128137",
        "1359912421",
        "3125421639",
        "1293138521",
        "2311944581"
    )
    val testAnswer1 = doChallenge(test1)
    println("testAnswer1: $testAnswer1")
    assert(testAnswer1 == 40)

    val lines = getLines("$day-input")
    val answer = doChallenge(lines)
    println("$day-Part1 answer: $answer")
}

private fun doChallenge(content: List<String>): Int {
    val array = content.map { it.map { c -> c.digitToInt() } }
    val map = Array(array.size) { Array(array.first().size) { Int.MAX_VALUE } }
    map[0][0] = 0

    val height = array.size
    val width = array[0].size
    val unvisited = (0 until height).flatMap { i -> (0 until width).map { j -> Pair(i, j) } }.toMutableSet()
    val end = Pair(height - 1, width - 1)

    while (unvisited.isNotEmpty()) {
        val current = getCurrent(map, unvisited)
        unvisited.remove(current)
        if (current == end) break
        val neighbors = getNeighbors(current, unvisited)
        neighbors.forEach { n ->
            val total = map[current.first][current.second] + array[n.first][n.second]
            if (total < map[n.first][n.second]){
                map[n.first][n.second] = total
            }
        }
    }

    return map[height - 1][width - 1]
}

private fun getNeighbors(current: Pair<Int, Int>, unvisited: MutableSet<Pair<Int, Int>>): List<Pair<Int, Int>> {
    val (x, y) = current
    val neighbors = listOf(Pair(x + 1, y), Pair(x - 1, y), Pair(x, y + 1), Pair(x, y - 1))
    return unvisited.filter { neighbors.contains(it) }
}

private fun getCurrent(map: Array<Array<Int>>, unvisited: Set<Pair<Int, Int>>): Pair<Int, Int> {
    return unvisited.map { it to map[it.first][it.second] }.minByOrNull { it.second }!!.first
}
