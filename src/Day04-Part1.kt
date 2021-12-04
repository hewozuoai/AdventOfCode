fun main() {

    var lines = getLines("Day04-input")
    val ballsDrawn = lines[0].split(",")

    lines = lines.drop(2)
    lines = lines.filter { it.isNotEmpty() }

    val boards = createBoards(lines)
    var answer = 0

    // play bingo!
    for (ball in ballsDrawn) {
        boards.forEach {
            if (answer == 0) {
                it.checkMarkNumber(ball)
                if (it.isWinner()) {
                    val sumOfUncalled = it.sumUncalledNumbers()
                    answer = sumOfUncalled * ball.toInt()
                }
            }
        }
    }

    println("Day4-Part1 answer: $answer")
}
