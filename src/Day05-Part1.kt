import java.lang.Integer.max
import java.lang.Integer.min

fun main() {

    val test = listOf(
        "0,9 -> 5,9",
        "8,0 -> 0,8",
        "9,4 -> 3,4",
        "2,2 -> 2,1",
        "7,0 -> 7,4",
        "6,4 -> 2,0",
        "0,9 -> 2,9",
        "3,4 -> 1,4",
        "0,0 -> 8,8",
        "5,5 -> 8,2"
    )
    val testAnswer = doChallenge(test)
    println("testAnswer: $testAnswer")
    assert(testAnswer == 5)

    val lines = getLines("Day05-input")
    val answer = doChallenge(lines)
    println("Day5-Part1 answer: $answer")
}

private fun doChallenge(content: List<String>): Int {

    val lines = content.map { Line(it) }
    val filtered = lines.filter { it.x1() == it.x2() || it.y1() == it.y2() }
    val points = collectPoints(filtered)
    return points.groupingBy { it }.eachCount().filter { it.value > 1 }.size
}

private fun collectPoints(filtered: List<Line>): ArrayList<Pair<Int, Int>> {
    val points = arrayListOf<Pair<Int, Int>>()
    for (line in filtered) {
        points.addAll(getPointsForLine(line))
    }
    return points
}

private fun getPointsForLine(line: Line): Collection<Pair<Int, Int>> {
    val points = arrayListOf<Pair<Int, Int>>()
    if (line.x1() == line.x2()) {
        for(i in min(line.y1(), line.y2())..max(line.y1(), line.y2())) {
            points.add(Pair(line.x1(), i))
        }
    } else {
        for(i in min(line.x1(), line.x2())..max(line.x1(), line.x2())) {
            points.add(Pair(i ,line.y1()))
        }
    }
    return points
}
