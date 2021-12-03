import java.io.File
import java.math.BigInteger
import java.security.MessageDigest

/**
 * Reads lines from the given input txt file.
 */
fun readInput(name: String) = File("src", "$name.txt").readLines()

/**
 * Converts string to md5 hash.
 */
fun String.md5(): String = BigInteger(1, MessageDigest.getInstance("MD5").digest(toByteArray())).toString(16)

fun getLinesAsInts(file: String): List<Int> {
    return readInput(file).map { it.toInt() }
}

fun getLines(file: String): List<String> {
    return readInput(file)
}