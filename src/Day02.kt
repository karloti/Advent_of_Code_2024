import kotlin.math.absoluteValue

fun main() {
    val report = mutableListOf<List<Int>>()

    fun load(fileName: String) {
        report.clear()
        readInput(fileName).map { line -> report.add(line.split(Regex("\\s+")).map(String::toInt)) }
    }

    fun List<Int>.checkSafe(): Int = zipWithNext { a, b ->
        when (b - a) {
            in 1..3 -> 1
            in -3..-1 -> -1
            else -> 0
        }
    }.run { if (sum().absoluteValue == size) 1 else 0 }

    fun part1(): Int = report.sumBy { levels: List<Int> -> levels.checkSafe() }

    fun part2(): Int = report.sumBy { levels: List<Int> ->
        levels.indices.asSequence().map { i ->
            levels.toMutableList().run {
                removeAt(i)
                checkSafe()
            }
        }.firstOrNull { it == 1 } ?: 0
    }

    load("Day02_test")
    check(part1() == 2) { "incorrect result part1" }
    check(part2() == 4) { "incorrect result part2" }

    load("Day02")
    part1().println()
    part2().println()
}