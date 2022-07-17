package aoc_2020.day01

import readInput

fun main() {
    fun part1(input: List<String>): Int {
        val list = input.map { it.toInt() }.sorted()

        var p1 = 0
        var p2 = list.size - 1

        while (p1 < p2) {
            val sum = list[p1] + list[p2]
            if (sum == 2020) break

            if (sum < 2020) p1++
            if (sum > 2020) p2--
        }

        return list[p1] * list[p2]
    }

    fun part2(input: List<String>): Int {
        val list = input.map { it.toInt() }.sorted()
        var p1 = 0
        var p2 = 0
        var p3 = 0
        for (i in input.indices) {
            p1 = i
            p2 = i + 1
            p3 = input.size - 1
            var sum = 0
            while (p2 < p3) {
                sum = list[p1] + list[p2] + list[p3]
                if (sum == 2020) break

                if (sum < 2020) p2++
                if (sum > 2020) p3--
            }
            if (sum == 2020) break
        }

        return list[p1] * list[p2] * list[p3]
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("/day01/Day01_test")
    check(part1(testInput) == 514579)
    check(part2(testInput) == 241861950)

    val input = readInput("/day01/Day01")
    println(part1(input))
    println(part2(input))
}
