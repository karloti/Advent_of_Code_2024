import kotlin.math.absoluteValue

fun main() {
    val s1 = mutableListOf<Int>()
    val s2 = mutableListOf<Int>()

    fun load(fileName: String) {
        s1.clear()
        s2.clear()
        readInput(fileName).map { line ->
            line.split(Regex("\\s+"))
                .map(String::toInt)
                .let { (e1, e2) -> s1.add(e1); s2.add(e2) }
        }
    }

    load("Day01_test")

    fun part1(): Int {
        s1.sort()
        s2.sort()
        return (s1 zip s2).sumOf { (a, b) -> (a - b).absoluteValue }
    }

    fun part2(): Int {
        val map = s2.groupingBy { it }.eachCount()
        return s1.sumOf { it * (map[it] ?: 0) }
    }

    part1().let { result ->
        println("result1 = $result")
        check(result == 11) { "part1 Day01_test" }
    }

    part2().let { result ->
        println("result2 = $result")
        check(result == 31) { "part2 Day01_test" }
    }

    load("Day01")

    part1().println()
    part2().println()
}