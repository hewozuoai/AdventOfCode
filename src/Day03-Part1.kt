fun main() {

    val lines = getLines("Day03-input")
    val zero = arrayListOf(0,0,0,0,0,0,0,0,0,0,0,0)
    val one = arrayListOf(0,0,0,0,0,0,0,0,0,0,0,0)

    for (line in lines) {
        for (i in line.indices) {
            if (line[i].digitToInt() == 0){
                zero[i] += 1
            } else {
                one[i] += 1
            }
        }
    }

    val (gamma, epsilon) = buildGammaEpsilon(zero, one)

    val answer = convertBinaryToDecimal(gamma) * convertBinaryToDecimal(epsilon)
    println("Day3-Part1 answer: $answer")
}

private fun buildGammaEpsilon(zero: ArrayList<Int>, one: ArrayList<Int>): Pair<String, String> {
    var gamma = ""
    var epsilon = ""

    for (i in zero.indices) {
        if (zero[i] > one[i]) {
            gamma += "1"
            epsilon += "0"
        } else {
            gamma += "0"
            epsilon += "1"
        }
    }
    return Pair(gamma, epsilon)
}