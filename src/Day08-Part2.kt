fun main() {

    val test = listOf(
        "be cfbegad cbdgef fgaecd cgeb fdcge agebfd fecdb fabcd edb | fdgacbe cefdb cefbgd gcbe", // be = cf   gcbe == bdcf
        "edbfga begcd cbg gc gcadebf fbgde acbgfd abcde gfcbed gfec | fcgedb cgb dgebacf gc",
        "fgaebd cg bdaec gdafb agbcfd gdcbef bgcad gfac gcb cdgabef | cg cg fdcagb cbg",
        "fbegcd cbd adcefb dageb afcb bc aefdc ecdab fgdeca fcdbega | efabcd cedba gadfec cb",
        "aecbfdg fbg gf bafeg dbefa fcge gcbea fcaegb dgceab fcbdga | gecf egdcabf bgf bfgea",
        "fgeab ca afcebg bdacfeg cfaedg gcfdb baec bfadeg bafgc acf | gebdcfa ecba ca fadegcb",
        "dbcfg fgd bdegcaf fgec aegbdf ecdfab fbedc dacgb gdcebf gf | cefg dcbef fcge gbcadfe",
        "bdfegc cbegaf gecbf dfcage bdacg ed bedf ced adcbefg gebcd | ed bcgafe cdgba cbgef",
        "egadfb cdbfeg cegd fecab cgb gbdefca cg fgcdab egfdb bfceg | gbdfcae bgc cg cgb",
        "gcafb gcf dcaebfg ecagb gf abcdeg gaef cafbge fdbac fegbdc | fgae cfgab fg bagce"
    )
    val testAnswer = doChallenge(test)
    println("testAnswer: $testAnswer")
    assert(testAnswer == 61229)

    val lines = getLines("Day08-input")
    val answer = doChallenge(lines)
    println("Day8-Part2 answer: $answer")
}

//  0000    aaaa
// 1    2  b    c
// 1    2  b    c
//  3333    dddd
// 4    5  e    f
// 4    5  e    f
//  6666    gggg

private fun doChallenge(content: List<String>): Int {

    var answer = 0
    content.forEach { line ->

        val (signals, entry) = line.split(" | ").map { it.split(" ") }
        val map = mutableMapOf(
            1 to signals.first { it.length == 2 },
            4 to signals.first { it.length == 4 },
            7 to signals.first { it.length == 3 },
            8 to signals.first { it.length == 7 }
        )

        // length = 6
        map[6] = signals.first { it.length == 6 && !hasCharacters(it, map[1]!!)}
        map[9] = signals.first { it.length == 6 && hasCharacters(it, map[4]!!)}
        map[0] = signals.first { it.length == 6 && !map.containsValue(it) }

        // length 5
        map[3] = signals.first { it.length == 5 && hasCharacters(it, map[1]!!) }
        map[5] = signals.first { it.length == 5 && hasSameCharacters(it + map[1]!!, map[9]!!) }

        // last one!
        map[2] = signals.first { !map.containsValue(it) }

        answer += entry.map { x -> map.filterValues { hasSameCharacters(it, x) }.keys.first() }.joinToString("").toInt()
    }

    return answer
}

fun hasSameCharacters(first: String, second: String): Boolean {
    return hasCharacters(first, second) && hasCharacters(second, first)
}

private fun hasCharacters(first: String, second: String) : Boolean {
    return second.all { first.contains(it) }
}