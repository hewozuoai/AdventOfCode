fun main() {

    val test = listOf(
        "[({(<(())[]>[[{[]{<()<>>",
        "[(()[<>])]({[<{<<[]>>(",
        "{([(<{}[<>[]}>{[]{[(<()>",
        "(((({<>}<{<{<>}{[]{[]{}",
        "[[<[([]))<([[{}[[()]]]",
        "[{[{({}]{}}([{[{{{}}([]",
        "{<[[]]>}<{[{[{[]{()[[[]",
        "[<(<(<(<{}))><([]([]()",
        "<{([([[(<>()){}]>(<<{{",
        "<{([{{}}[<[[[<>{}]]]>[]]"
    )
    val testAnswer = doChallenge(test)
    println("testAnswer: $testAnswer")
    assert(testAnswer == 26397)

    val lines = getLines("Day10-input")
    val answer = doChallenge(lines)
    println("Day10-Part1 answer: $answer")
}

private fun doChallenge(content: List<String>): Int {
    var answer = 0
    val closers = arrayListOf(")","]","}",">")
    val points = arrayListOf(3, 57, 1197, 25137)
    content.forEach { line ->
        var temp = line
        while(temp.contains("()") || temp.contains("[]") || temp.contains("{}") || temp.contains("<>"))
        {
            temp = temp.replace("\\(\\)".toRegex(), "")
            temp = temp.replace("\\{}".toRegex(), "")
            temp = temp.replace("\\[]".toRegex(), "")
            temp = temp.replace("<>".toRegex(), "")
        }
        var score = 0
        var lastPosition = temp.length
        for (i in 0 until closers.size) {
            val position = temp.indexOf(closers[i])
            if (position != -1 && position < lastPosition) {
                lastPosition = position
                score = points[i]
            }
        }
        answer += score
    }
    return answer
}
