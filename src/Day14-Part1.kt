
fun main() {
    val day = "Day14"
    val test1 = listOf(
        "NNCB",
        "",
        "CH -> B",
        "HH -> N",
        "CB -> H",
        "NH -> C",
        "HB -> C",
        "HC -> B",
        "HN -> C",
        "NN -> C",
        "BH -> H",
        "NC -> B",
        "NB -> B",
        "BN -> B",
        "BB -> N",
        "BC -> B",
        "CC -> N",
        "CN -> C"
    )
    val testAnswer1 = doChallenge(test1)
    println("testAnswer1: $testAnswer1")
    assert(testAnswer1 == 1588)

    val lines = getLines("$day-input")
    val answer = doChallenge(lines)
    println("$day-Part1 answer: $answer")
}

private const val LOOP_COUNT = 10

private fun doChallenge(content: List<String>): Int {
    var template = content.first()
    val instructions = content.filter { it.contains(" -> ") }.map { it.split(" -> ") }.map { it[0] to it[1] }.toSet()

    for (i in 1..LOOP_COUNT) {
        var newString = "${template.first()}"
        for (t in 0..template.length - 2) {
           val lookup = "${template[t]}${template[t + 1]}"
            newString += "${instructions.first { it.first == lookup }.second}${template[t + 1]}"
        }
        template = newString
    }

    val mostCommon = template.groupBy { it }.map { it.value }.maxByOrNull { it.size }!!.size
    val leastCommon = template.groupBy { it }.map { it.value }.minByOrNull { it.size }!!.size

    return mostCommon - leastCommon
}
