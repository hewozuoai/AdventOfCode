
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
    assert(testAnswer1 == 2188189693529L)

    val lines = getLines("$day-input")
    val answer = doChallenge(lines)
    println("$day-Part2 answer: $answer")
}

private const val LOOP_COUNT = 40

private fun doChallenge(content: List<String>): Long {
    val template = content.first()
    val instructions = createInstructions(content)
    var pairs = createPairs(template)

    repeat(LOOP_COUNT) {
        pairs = buildMap {
            for ((pair, count) in pairs) {
                for (newPair in instructions.getValue(pair) ) {
                    put(newPair, getOrElse(newPair) { 0 } + count)
                }
            }
        }
    }

    val counts = calculateCounts(template, pairs)
    return (counts.values.maxOrNull()!! - counts.values.minOrNull()!!) / 2
}

private fun createPairs(template: String) = template.zip(template.drop(1)) { a, b -> "$a$b" }.groupingBy { it }.eachCount().mapValues { it.value.toLong() }

private fun calculateCounts(
    template: String,
    instructionCounts: Map<String, Long>
): Map<Char, Long> {
    return buildMap<Char, Long> {
        put(template.first(), 1)
        put(template.last(), getOrElse(template.last()) { 0 } + 1)
        for ((pair, count) in instructionCounts) {
            for (i in pair) {
                put(i, getOrElse(i) { 0 } + count)
            }
        }
    }
}

private fun createInstructions(content: List<String>): Map<String, List<String>> {
    val instructions = content.drop(2).associate { l ->
        val (first, second) = l.split(" -> ", limit = 2)
        first to listOf("${first.first()}${second}", "${second}${first.last()}")
    }
    return instructions
}