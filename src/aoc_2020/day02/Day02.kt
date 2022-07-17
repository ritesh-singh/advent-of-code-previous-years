package aoc_2020.day02

import readInput

fun main() {
    fun part1(input: List<String>): Int {
        var totalValid = 0
        input.forEach {
            val puzzleInp = it.split(":").map { value -> value.trim() }
            val passwordPolicy = puzzleInp[0].split(" ").map { value -> value.trim() }

            val lowestAndHighest = passwordPolicy[0].split("-").map { value -> value.trim() }.map { value -> value.toInt() }
            val charToMatch = passwordPolicy[1]

            val password = puzzleInp[1]

            val count = password.count { char -> char.toString() == charToMatch }

            if (count in lowestAndHighest[0]..lowestAndHighest[1]) totalValid++
        }
        return totalValid
    }

    fun part2(input: List<String>): Int {
        var totalValid = 0
        input.forEach {
            val puzzleInp = it.split(":").map { value -> value.trim() }
            val passwordPolicy = puzzleInp[0].split(" ").map { value -> value.trim() }

            val lowestAndHighest = passwordPolicy[0].split("-").map { value -> value.trim() }.map { value -> value.toInt() }
            val charToMatch = passwordPolicy[1]

            val password = puzzleInp[1]

            var count = 0
            password.forEachIndexed { index, c ->
                if (
                    (index == lowestAndHighest[0]-1 || index == lowestAndHighest[1]-1)
                    &&
                    c.toString() == charToMatch
                ) count++
            }
            if (count == 1) totalValid++
        }
        return totalValid
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("/day02/Day02_test")
    println(part1(testInput))
    println(part2(testInput))

    val input = readInput("/day02/Day02")
    println(part1(input))
    println(part2(input))

}
