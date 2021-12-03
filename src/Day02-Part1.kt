fun main() {

    val lines = getLines("Day02-input")

    var horizontal = 0
    var depth = 0

    for (line in lines) {
        val value = line.takeLast(1).toInt()
        if (line.contains("forward")) horizontal += value
        if (line.contains("down")) depth += value
        if (line.contains("up")) depth -= value
    }

    val answer = horizontal * depth

    println("Day2-Part1 answer: $answer")
}
