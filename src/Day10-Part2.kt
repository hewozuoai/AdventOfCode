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
    assert(testAnswer == 288957L)

    val lines = getLines("Day10-input")
    val answer = doChallenge(lines)
    println("Day10-Part2 answer: $answer")
}

private fun doChallenge(content: List<String>): Long {
    var answer: Long = 0
    val openers = listOf(Pair('(', 1), Pair('[', 2), Pair('{', 3), Pair('<', 4))
    val closers = arrayListOf(")","]","}",">")
    val points = arrayListOf(3, 57, 1197, 25137)
    val incomplete = mutableListOf<String>()
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
        if (temp.isNotEmpty() && score == 0) {
            incomplete.add(temp)
        }
    }

    val scores = mutableListOf<Long>()
        for (l in incomplete) {
            var score: Long = 0
            val charArray = l.reversed().toCharArray()
            for (c in charArray) {
                score *= 5
                score += openers.first { it.first == c }.second
            }
            scores.add(score)
        }
        if (scores.isNotEmpty()) {
            scores.sort()
            answer = scores[scores.size / 2]
        }
    return answer
}
