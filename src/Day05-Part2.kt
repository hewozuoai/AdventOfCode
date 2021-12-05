import java.lang.Integer.max
import java.lang.Integer.min
import kotlin.math.*

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
    assert(testAnswer == 12)

    val lines = getLines("Day05-input")
    val answer = doChallenge(lines)
    println("Day5-Part2 answer: $answer")
}

private fun doChallenge(content: List<String>): Int {

    val lines = content.map { Line(it) }
    val points = collectPoints(lines)
    return points.groupingBy { it }.eachCount().filter { it.value > 1 }.size
}

private fun collectPoints(lines: List<Line>): ArrayList<Pair<Int, Int>> {
    val points = arrayListOf<Pair<Int, Int>>()
    for (line in lines) {
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
    } else if(line.y1() == line.y2()) {
        for(i in min(line.x1(), line.x2())..max(line.x1(), line.x2())) {
            points.add(Pair(i ,line.y1()))
        }
    } else {
        //diagonal line
        var x = line.x1()
        var y = line.y1()
        val slope = (line.y2() - line.y1()) / (line.x2() - line.x1())
        if (slope == 1 || slope == -1) {
            if (line.x1() < line.x2() && line.y1() < line.y2()) {
                while (x <= line.x2() && y <= line.y2()) {
                    points.add(Pair(x, y))
                    x += 1
                    y += 1
                }
            } else if (line.x1() > line.x2() && line.y1() < line.y2()) {
                while (x >= line.x2() && y <= line.y2()) {
                    points.add(Pair(x, y))
                    x -= 1
                    y += 1
                }
            } else if (line.x1() < line.x2() && line.y1() > line.y2()) {
                while (x <= line.x2() && y >= line.y2()) {
                    points.add(Pair(x, y))
                    x += 1
                    y -= 1
                }
            } else if (line.x1() > line.x2() && line.y1() > line.y2()) {
                while (x >= line.x2() && y >= line.y2()) {
                    points.add(Pair(x, y))
                    x -= 1
                    y -= 1
                }
            }
        }
    }

    return points
}
