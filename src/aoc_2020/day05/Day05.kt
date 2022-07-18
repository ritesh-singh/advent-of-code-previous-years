package aoc_2020.day05

import readInput
import kotlin.math.ceil
import kotlin.math.floor

fun main() {

    fun getSeatId(boardingPass:String):Int{
        val (firstSeven, lastThree) = boardingPass.chunked(7)
        var row = 0..127
        firstSeven.forEach {
            when(it){
                'F' -> {
                    row = row.first..floor((row.last + row.first) / 2.0).toInt()
                }
                'B' -> {
                    row = ceil((row.last+row.first) /2.0).toInt()..row.last
                }
            }
        }

        var column = 0..7
        lastThree.forEach {
            when(it){
                'L' -> {
                    column = column.first..floor((column.last + column.first) / 2.0).toInt()
                }
                'R' -> {
                    column = ceil((column.last+column.first) /2.0).toInt()..column.last
                }
            }
        }

        return (row.first * 8) + column.first
    }

    fun part1(input: List<String>): Int {
        var highestSeatId = Int.MIN_VALUE
        input.forEach { boardingpass ->
            val seatId = getSeatId(boardingpass)
            if (seatId > highestSeatId){
                highestSeatId = seatId
            }
        }
        return highestSeatId
    }

    fun part2(input: List<String>): Int {
        val list = mutableListOf<Int>()
        input.forEach { boardingpass ->
            list.add(getSeatId(boardingpass))
        }
        list.sort()
        list.forEachIndexed { index, i ->
            if (index == 0 || index == list.size-1) return@forEachIndexed
            if (i != list[index-1]+1)
                return i-1
            if (i != list[index+1]-1)
                return i+1
        }

        return 0
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("/day05/Day05_test")
    println(part1(testInput))
    println(part2(testInput))

    println("--------")

    val input = readInput("/day05/Day05")
    println(part1(input))
    println(part2(input))

}
