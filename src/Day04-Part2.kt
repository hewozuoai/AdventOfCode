fun main() {

    var lines = getLines("Day04-input")
    val ballsDrawn = lines[0].split(",")

    lines = lines.drop(2)
    lines = lines.filter { it.isNotEmpty() }

    val boards = createBoards(lines)
    var answer = 0

    // play bingo!
    for (ball in ballsDrawn) {
        val filtered = boards.filter { !it.isWinner() }
        if (filtered.count() == 1) {
            val board = filtered.first()
            val restOfBalls = ballsDrawn.drop(ballsDrawn.indexOf(ball))
            for (lastBall in restOfBalls) {
                board.checkMarkNumber(lastBall)
                if (board.isWinner()) {
                    answer = board.sumUncalledNumbers() * lastBall.toInt()
                    break
                }
            }
            break
        } else {
            filtered.forEach {
                it.checkMarkNumber(ball)
            }
        }
    }

    println("Day4-Part2 answer: $answer")
}