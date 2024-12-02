import kotlin.math.absoluteValue
import kotlin.time.measureTime

fun main() {
    val report = mutableListOf<List<Int>>()

    fun load(fileName: String) {
        report.clear()
        readInput(fileName).map { line -> report.add(line.split(Regex("\\s+")).map(String::toInt)) }
    }

    fun direction(b: Int, a: Int) = when (b - a) {
        in 1..3 -> 1
        in -3..-1 -> -1
        else -> 0
    }

    fun Iterable<Int>.checkSafe(): Int = zipWithNext { a, b -> direction(b, a) }
        .run { if (sum().absoluteValue == size) 1 else 0 }

    fun Iterable<Int>.checkSafe2(): Int {
        var lastElement: Int? = null
        var lastDirection: Int? = null
        var countCorrection = 0
        return zipWithNext { a, b ->
            val result = when {
                direction(b, a) == lastDirection -> 1
                lastElement == null -> 0
                direction(b, lastElement!!) == lastDirection -> run {
                    countCorrection++
                    if (countCorrection == 1) 1 else 0
                }

                else -> 0
            }
            lastElement = a
            lastDirection = lastDirection ?: direction(b, a).takeIf { it != 0 }
            result
        }.run { if (sum().absoluteValue == size - 1) 1 else 0 }
    }

    fun part1(): Int = report.sumOf { levels: List<Int> -> levels.checkSafe() }

    fun part2(): Int = report.sumOf { levels: List<Int> ->
        levels.indices.asSequence().map { i ->
            levels.toMutableList().run {
                removeAt(i)
                checkSafe()
            }
        }.firstOrNull { it == 1 } ?: 0
    }

    fun part2New(): Int = report.sumOf { levels: List<Int> -> levels.checkSafe2() }

    load("Day02_test")
    check(part1() == 2) { "incorrect result part1" }
    check(part2() == part2New()) { "incorrect result part2New" }

    load("Day02")
    part1().println()
    part2().println()
    part2New().println()

    measureTime {
        repeat(100_000) { part2New() }
    }.let(::println)
    measureTime {
        repeat(100_000) { part2() }
    }.let(::println)
}