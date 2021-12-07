import java.math.BigInteger

fun main() {

    val test = "3,4,3,1,2"
    val testAnswer = doChallenge(test)
    println("testAnswer: $testAnswer")
    assert(testAnswer == 26_984_457_539)

    val lines = getLines("Day06-input").first()
    val answer = doChallenge(lines)
    println("Day6-Part2 answer: $answer")
}

private fun doChallenge(content: String): Long {

    val fishes = content.split(",").map { LanternFish(it.toInt()) }.toMutableList()
    val days = 256

    var fishState = MutableList(9) { BigInteger.ZERO }
    fishes.forEach { fishState[it.life]++ }

    repeat(days) {
        val newFishState = MutableList(9) { BigInteger.ZERO }

        (8 downTo 0).forEach {
            if (it == 0) {
                newFishState[6] += fishState[0]
                newFishState[8] += fishState[0]
                return@forEach
            }
            newFishState[it - 1] += fishState[it]
        }
        fishState = newFishState
    }

    return fishState.sumOf { it }.toLong()
}
