fun main() {

    val lines = getLines()
    var count = 0

    for (i in lines.indices) {
        if (i > lines.size -2) break
        val firstNumber = lines[i]
        val secondNumber = lines[i+1]
        if (secondNumber > firstNumber) count += 1
    }
    println("Day1 answer: $count")
}
