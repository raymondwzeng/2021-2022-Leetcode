class Solution {
    
    data class Cell(val column: Int, val row: Int){}
    
    fun BFSWithReplacement(column: Int, row: Int, matrix: MutableList<MutableList<Int>>) {
        val queue = mutableListOf<Cell>(Cell(column, row))
        while(queue.isNotEmpty()) {
            val front = queue.first()
            
            // println("Current cell is at ${front.column}, ${front.row}")
            // println("Source minutes: ${matrix[front.column][front.row]}")
            
            if(front.column - 1 >= 0 && matrix[front.column - 1][front.row] > matrix[front.column][front.row] + 1) {
                matrix[front.column - 1][front.row] = matrix[front.column][front.row] + 1
                queue.add(Cell(front.column - 1, front.row))
            }
            if(front.column + 1 < matrix.size && matrix[front.column + 1][front.row] > matrix[front.column][front.row] + 1) {
                matrix[front.column + 1][front.row] = matrix[front.column][front.row] + 1
                queue.add(Cell(front.column + 1, front.row))
            }
            if(front.row - 1 >= 0 && matrix[front.column][front.row - 1] > matrix[front.column][front.row] + 1) {
                matrix[front.column][front.row - 1] = matrix[front.column][front.row] + 1
                queue.add(Cell(front.column, front.row - 1))
            }
            if(front.row + 1 < matrix[front.column].size && matrix[front.column][front.row + 1] > matrix[front.column][front.row] + 1) {
                matrix[front.column][front.row + 1] = matrix[front.column][front.row] + 1
                queue.add(Cell(front.column, front.row + 1))
            }
            
            queue.removeAt(0)
        }
    }
    
    fun orangesRotting(grid: Array<IntArray>): Int {
        //Similar to 01 matrix
        //Perform BFS (because that will be the fastest) on each rotting orange
        //Copy the matrix in some way -> Probably mark the oranges in a specific manner
            //Rotting orange -> 0
            //Fresh orange -> 100
            //Empty cell -> -1
        //Iterate over the array, performing BFS at each rotting orange initially
        //If the value of a cell is greater than the value that we want to override it with, then do so, and add its neighbors
        //Iterate over the matrix one last time, collecting the maximum value (if it happens to be 100, return -1 instead)
        //O(m * n) to copy the array, O(m * n) for BFS on the array, O(m * n) again for finding the max
        //O(m * n * r), where m, n are the dimensions of the array, r is the number of rotting oranges -> Unsure on this one
        //O(m * n) space
        val rotMatrix = MutableList<MutableList<Int>>(grid.size) {column -> MutableList<Int>(grid[column].size){ row ->
            if(grid[column][row] == 0) {
                -1
            } else if(grid[column][row] == 1) {
                100
            } else { //Represent the "number of minutes" until the cell is rotten
                0
            }
        }
        }
        
        rotMatrix.forEachIndexed {column, rowList ->
            rowList.forEachIndexed {row, value ->
                if(grid[column][row] == 2) BFSWithReplacement(column, row, rotMatrix)
            }
        }
        
        var max = 0
        rotMatrix.forEach {rowList ->
            rowList.forEach {value ->
                // print(value)
                // print(" ")
                if(value == 100) return -1
                if(value > max) max = value
            }
            // println()
        }
        
        return max
    }
}