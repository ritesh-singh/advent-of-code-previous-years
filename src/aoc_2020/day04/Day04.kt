package aoc_2020.day04

import readInput

fun main() {

    val expectedFields = hashSetOf(
        "byr",
        "iyr",
        "eyr",
        "hgt",
        "hcl",
        "ecl",
        "pid",
//        "cid"
    )

    fun isValid(field: String,value:String):Boolean {
        when(field){
            "byr" -> return value.length == 4 && value.toInt() in 1920..2002
            "iyr" -> return value.length == 4 && value.toInt() in 2010..2020
            "eyr" -> return value.length == 4 && value.toInt() in 2020..2030
            "hgt" -> {
                val number = value.takeWhile { it.isDigit() }
                val str = value.takeLastWhile { !it.isDigit() }
                if (number.isBlank() || str.isBlank()) return false
                if (str == "cm"){
                    return number.toInt() in 150..193
                }

                if (str == "in"){
                    return number.toInt() in 59..76
                }

                return false
            }
            "hcl" -> {
                if (value.length != 7) return false
                if (value[0] == '#' && value.substring(1,value.length).contains("[0-9a-f]".toRegex())){
                    return true
                }
                return false
            }
            "ecl" -> {
                val set = hashSetOf("amb","blu","brn","gry","grn","hzl","oth")
                return set.contains(value)
            }
            "pid" -> {
               return value.matches("[0-9]{9}".toRegex())
            }
            "cid" -> {
                return true
            }
            else -> return false
        }
    }

    fun part1(input: List<String>): Int {
        var valid = 0
        val set = hashSetOf<String>()

        input.forEach {
            it.split(" ")
                .map { it.trim() }
                .map {
                    set.add(it.split(":")[0])
                }

            if (it.isBlank()) {
                if(expectedFields.intersect(set).count() >= 7){
                    valid++
                }
                set.clear()
            }
        }

        return valid
    }

    fun part2(input: List<String>): Int {
        var valid = 0
        val set = hashSetOf<String>()
        input.forEach {
            it.split(" ")
                .filter { it.isNotBlank() }
                .map { it.trim() }
                .map {
                    val values = it.split(":").map { it.trim() }
                    if (isValid(values[0],values[1])){
                        set.add(values[0])
                    }
                }

            if (it.isBlank()) {
                if(expectedFields.intersect(set).count() >= 7){
                    valid++
                }
                set.clear()
            }
        }

        return valid
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("/day04/Day04_test")
    println(part1(testInput))
    println(part2(testInput))

    println("--------")

    val input = readInput("/day04/Day04")
    println(part1(input))
    println(part2(input))

}
