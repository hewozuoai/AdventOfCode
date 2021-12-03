fun main() {

    val lines = getLinesAsInts("Day01-input")
    var count = 0

    for (i in lines.indices) {
        if (i > lines.size -4) break
        val firstThree = lines[i] + lines[i+1] + lines[i+2]
        val nextThree = lines[i+1] + lines[i+2] + lines[i+3]
        if (nextThree > firstThree) count += 1
    }
    println("Day1-Part2 answer: $count")
}
