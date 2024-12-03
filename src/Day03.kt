fun main() {
    var lines: List<String> = emptyList()
    val instructionRegex = Regex("""mul\((\d{1,3}),(\d{1,3})\)""")
    val dontComputeRegex = Regex("""(don't\(\).*?do\(\))|$""")

    fun String.compute(): Int = instructionRegex.findAll(this).sumOf { match ->
        val (a, b) = match.destructured
        (a.toInt() * b.toInt())
    }

    fun part1(): Int = lines.sumOf(String::compute)
    fun part2(): Int = lines.joinToString("").split(dontComputeRegex).sumOf(String::compute)

    lines = readInput("Day03_test")
    check(part1() == 161) { "incorrect result part1" }
    check(part2() == 48) { "incorrect result part2" }

    lines = readInput("Day03")
    part1().println()
    part2().println()
}