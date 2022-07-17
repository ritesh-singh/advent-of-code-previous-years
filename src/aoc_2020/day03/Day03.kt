package aoc_2020.day03

import readInput

private fun Array<CharArray>.printMatrix() {
    for (i in 0 until this.size) {
        for (j in 0 until this[0].size) {
            print(this[i][j])
            print(" ")
        }
        println()
    }
}

private fun List<String>.initMatrix(matrix: Array<CharArray>) {
    val rowSize = size
    val colSize = this[0].length
    for (row in 0 until rowSize) {
        for (col in 0 until colSize) {
            matrix[row][col] = this[row][col]
        }
    }
}


private fun Array<CharArray>.totalTrees(right: Int, down: Int): Int {
    var trees = 0
    var rowCount = 0
    var rowIndex = 0
    var colIndex = 0

    while (rowCount < this.size-1) {
        colIndex = (colIndex + right) % this[0].size
        rowIndex = (rowIndex + down) % this.size
        if (this[rowIndex][colIndex] == '#') trees++
        rowCount += down
    }

    return trees
}

fun main() {
    fun part1(input: List<String>): Int {
        val rowSize = input.size
        val colSize = input[0].length

        val matrix = Array(rowSize) { CharArray(colSize) }
        input.initMatrix(matrix = matrix)

        return matrix.totalTrees(right = 3, down = 1)
    }

    fun part2(input: List<String>): Long {
        val rowSize = input.size
        val colSize = input[0].length

        val matrix = Array(rowSize) { CharArray(colSize) }
        input.initMatrix(matrix = matrix)

        return matrix.totalTrees(right = 1, down = 1).toLong() *
                matrix.totalTrees(right = 3, down = 1) *
                matrix.totalTrees(right = 5, down = 1) *
                matrix.totalTrees(right = 7, down = 1) *
                matrix.totalTrees(right = 1, down = 2)
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("/day03/Day03_test")
    println(part1(testInput))
    println(part2(testInput))

    println("--------")

    val input = readInput("/day03/Day03")
    println(part1(input))
    println(part2(input))

}
