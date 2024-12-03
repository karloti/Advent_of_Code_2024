fun main() {
    var lines: List<String> = emptyList()
    val instructionRegex = Regex("""mul\((\d{1,3}),(\d{1,3})\)""")
    val dontComputeRegex = Regex("""(don't\(\).*?do\(\))|(don't\(\).*)""")

    fun load(fileName: String) {
        lines = readInput(fileName)
    }

    fun String.compute() = instructionRegex.findAll(this).map {
        val (a, b) = it.destructured
        (a.toInt() * b.toInt())
    }.sum()

    fun part1(): Int = lines.sumOf(String::compute)
    fun part2(): Int = lines.joinToString("").split(dontComputeRegex).sumOf(String::compute)

    load("Day03_test")
    check(part1() == 161) { "incorrect result part1" }
    check(part2() == 48) { "incorrect result part2" }

    load("Day03")
    part1().println()
    part2().println()
}