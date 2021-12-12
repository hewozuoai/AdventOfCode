
fun main() {
    val day = "Day12"
    val test1 = listOf(
        "start-A",
        "start-b",
        "A-c",
        "A-b",
        "b-d",
        "A-end",
        "b-end"
    )
    val testAnswer1 = doChallenge(test1)
    println("testAnswer1: $testAnswer1")
    assert(testAnswer1 == 36)

    val test2 = listOf(
        "dc-end",
        "HN-start",
        "start-kj",
        "dc-start",
        "dc-HN",
        "LN-dc",
        "HN-end",
        "kj-sa",
        "kj-HN",
        "kj-dc"
    )
    val testAnswer2 = doChallenge(test2)
    println("testAnswer2: $testAnswer2")
    assert(testAnswer2 == 103)

    val test3 = listOf(
        "fs-end",
        "he-DX",
        "fs-he",
        "start-DX",
        "pj-DX",
        "end-zg",
        "zg-sl",
        "zg-pj",
        "pj-he",
        "RW-he",
        "fs-DX",
        "pj-RW",
        "zg-RW",
        "start-pj",
        "he-WI",
        "zg-he",
        "pj-fs",
        "start-RW"
    )
    val testAnswer3 = doChallenge(test3)
    println("testAnswer3: $testAnswer3")
    assert(testAnswer3 == 3509)

    val lines = getLines("$day-input")
    val answer = doChallenge(lines)
    println("$day-Part2 answer: $answer")
}

private var answer = 0
private var grid = emptyMap<String, HashSet<String>>()

private const val START = "start"
private const val END = "end"

private fun doChallenge(content: List<String>): Int {
    grid = createPathGrid(content)
    answer = 0
    findPaths(START, HashSet(), false)
    return answer
}

private fun findPaths(currPath: String, visited: HashSet<String>, canReturn: Boolean) {
    if (currPath == END) {
        answer++
        return
    }
    for (nextPath in grid[currPath]!!) {
        if (nextPath == START) continue //ignore
        val isLowercase = nextPath.first().isLowerCase()
        var oldReturn = canReturn
        if (isLowercase) {
            if (nextPath in visited)  {
                if (canReturn) continue
                oldReturn = true
            } else {
                visited += nextPath
            }
        }
        findPaths(nextPath, visited, oldReturn)
        if (isLowercase && oldReturn == canReturn) {
            visited -= nextPath
        }
    }
}
