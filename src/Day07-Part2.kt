import kotlin.math.abs

fun main() {

    val test = "16,1,2,0,4,2,7,1,2,14"
    val testAnswer = doChallenge(test)
    println("testAnswer: $testAnswer")
    assert(testAnswer == 168)

    val lines = getLines("Day07-input").first()
    val answer = doChallenge(lines)
    println("Day7-Part2 answer: $answer")
}

private fun doChallenge(content: String): Int {
    val positions = content.split(",").map{ it.toInt() }
    val min = positions.minOrNull() ?: 0
    val max = positions.maxOrNull() ?: 0

    return (min..max).minOfOrNull { align ->
        positions.sumOf { pos ->
            val rate = abs(align - pos)
            rate * (rate + 1) / 2
        }
    } ?: 0
}
