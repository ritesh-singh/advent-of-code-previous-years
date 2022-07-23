package aoc_2020.day06

import readInput

fun main() {

    fun part1(input: List<String>): Int {
        var count = 0
        val set = mutableSetOf<Char>()
        input.forEach {
            it.forEach { set.add(it) }
            if (it.isBlank()) {
                count += set.size
                set.clear()
            }
        }
        count += set.size
        return count
    }

    fun part2(input: List<String>): Int {
        val list = mutableListOf<String>()
        var sum = 0
        input.forEachIndexed { index, string ->
            if (string.isNotBlank()) {
                list.add(string)
            }
            if (string.isBlank() || index == input.size - 1) {
                sum += if (list.size == 1) {
                    list[0].count()
                } else {
                    list.reduce { acc, s ->
                        acc.toSet().intersect(s.toSet()).joinToString()
                    }.let {
                        it.count { it.isLetterOrDigit() }
                    }
                }
                list.clear()
            }
        }

        return sum
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("/day06/Day06_test")
    println(part1(testInput))
    println(part2(testInput))

    println("--------")

    val input = readInput("/day06/Day06")
    println(part1(input))
    println(part2(input))

}
