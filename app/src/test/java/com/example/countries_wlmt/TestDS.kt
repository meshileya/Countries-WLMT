package com.example.countries_wlmt

import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Test

@ExperimentalCoroutinesApi
class TestDS {
    @Test
    fun addition_isCorrect() {
        val queue = ArrayDeque<Int>()
        queue.add(1)
        queue.offer(2)
        queue.removeFirst()

        val stack = mutableListOf<Int>()
        stack.push(1)
        stack.push(2)
        stack.pop()

        val stack1 = mutableListOf("7", "9")

        val root = TreeNode(1)
        root.left = TreeNode(2)
        root.right = TreeNode(3)
    }

    class TreeNode(val value: Int) {
        var left: TreeNode? = null
        var right: TreeNode? = null
    }

    fun spiralOrder(matrix: Array<IntArray>): List<Int> {
        var matrixElements = mutableListOf<Int>()

        for (i in 0 until 3){
            for (j in 0 until 3){
                matrixElements.add(matrix[i][j])
            }
        }
        return matrixElements
    }

    fun isValidSudoku(board: Array<CharArray>): Boolean {
        val seen = HashSet<String>()
        for (i in 0 until 9){
            for (j in 0 until 9){
                val num = board[i][j]
                if (num != '.'){
                    if (!seen.add("$num in row $i") || !seen.add("$num in column $j") || !seen.add("$num in sub-grid ${i/3}-${j/3}")){
                        return false
                    }
                }
            }
        }

        return true

    }

}