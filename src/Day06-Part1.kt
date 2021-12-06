fun main() {

    val test = "3,4,3,1,2"
    val testAnswer = doChallenge(test)
    println("testAnswer: $testAnswer")
    assert(testAnswer == 5934)

    val lines = getLines("Day06-input").first()
    val answer = doChallenge(lines)
    println("Day6-Part1 answer: $answer")
}

private fun doChallenge(content: String): Int {

    val fishes = content.split(",").map { LanternFish(it.toInt()) }.toMutableList()
    val days = 80

    for (i in 1..days) {
        val newFishes = fishes.count { it.shouldSpawnNewFish() }
        for (j in 1..newFishes) fishes.add(LanternFish())
        fishes.forEach { it.dayPassed() }
        println("Day $i - $fishes")
    }

    return fishes.count()
}

class LanternFish {
    var life: Int
    var updated: Boolean = false

    constructor() {
        this.life = 8
        this.updated = true
    }

    constructor(life: Int) {
        this.life = life
    }

    fun dayPassed() {
        if (!this.updated) this.life -= 1
        this.updated = false
    }

    fun shouldSpawnNewFish(): Boolean {
        if (this.life == 0){
            resetLife()
            return true
        }
        return false
    }

    private fun resetLife(){
        this.updated = true
        this.life = 6
    }

    override fun toString(): String {
        return this.life.toString()
    }

}
