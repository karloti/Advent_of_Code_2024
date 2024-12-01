import kotlin.math.absoluteValue

fun main() {
    val s1 = mutableListOf<Int>() // List to store numbers from the first column
    val s2 = mutableListOf<Int>() // List to store numbers from the second column

    /**
     * Loads input from a file into the s1 and s2 lists.
     *
     * @param fileName The name of the input file.
     */
    fun load(fileName: String) {
        s1.clear()
        s2.clear()
        readInput(fileName).map { line ->
            line.split(Regex("\\s+")) // Split the line by whitespace
                .map(String::toInt) // Convert strings to integers
                .let { (e1, e2) -> s1.add(e1); s2.add(e2) } // Add to respective lists
        }
    }

    /**
     * Solves Part 1 of the puzzle: Calculates the total distance between the lists.
     * Sorts the lists and calculates the absolute difference between corresponding elements.
     *
     * @return The total distance between the lists.
     */
    fun part1(): Int {
        s1.sort() // Sort the first list
        s2.sort() // Sort the second list
        return (s1 zip s2).sumOf { (a, b) -> (a - b).absoluteValue }
    }

    /**
     * Solves Part 2 of the puzzle: Calculates the similarity score between the lists.
     * Counts occurrences of each number in the second list and calculates the score.
     *
     * @return The similarity score between the lists.
     */
    fun part2(): Int {
        val map = s2.groupingBy { it }.eachCount()
        return s1.sumOf { it * (map[it] ?: 0) }
    }

    // Load input for the test case and verify the results
    load("Day01_test")

    part1().let { result ->
        println("result1 = $result")
        check(result == 11) { "part1 Day01_test" }
    }

    part2().let { result ->
        println("result2 = $result")
        check(result == 31) { "part2 Day01_test" }
    }

    // Load the actual input and solve the puzzle
    load("Day01")

    part1().println()
    part2().println()
}