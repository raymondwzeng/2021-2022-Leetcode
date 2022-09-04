/* Good 1st attempt, mildly brute force

    data class Cell(val row: Int, val col: Int)
    
    fun maximalSquare(matrix: Array<CharArray>, top: List<Cell>, right: List<Cell>): Int {
        val newTop = LinkedList<Cell>() //Inserting a lot at the end, so use linked list
        val newRight = LinkedList<Cell>()
        top.forEach { cell ->
            if(cell.row - 1 >= 0 && matrix[cell.row - 1][cell.col] == '1') {
                newTop.add(Cell(cell.row - 1, cell.col))
            } else {
                return top.size
            }
        }
        right.forEach { cell ->
            if(cell.col + 1 < matrix[cell.row].size && matrix[cell.row][cell.col + 1] == '1') {
                newRight.add(Cell(cell.row, cell.col + 1))
            } else {
                return right.size
            }
        }
        if(newTop.size == top.size && newRight.size == right.size) { //Check the corner if both lists are completely filled out
            val cornerCell = top[top.size - 1]
            if(cornerCell.row - 1 >= 0 && cornerCell.col + 1 < matrix[cornerCell.row].size && matrix[cornerCell.row - 1][cornerCell.col + 1] == '1') {
                val newCell = Cell(cornerCell.row - 1, cornerCell.col + 1)
                newTop.add(newCell)
                newRight.add(newCell)
                return maximalSquare(matrix, newTop, newRight)
            }
        }
        return top.size //Should be the same as right.size
    }

fun maximalSquare(matrix: Array<CharArray>): Int {
        /*
            Find the largest square with only values of 1, and return its area -> Length * Width or either squared
            m, n <= 300 --> O(mn)-ish runtime
            
            Brute force solution
                - Start with a side length of 1
                - Iterate over the entire matrix until you find a section that covers all of the squares that you want
                - If found, expand the side length by 1, and repeat (break)
                - If not, return the previous area recorded (side length - 1 squared)
            O(mn * s^2) where s is the side length of the array
            
            Repeated work -> If we can not form a square in our particular tile, then if the issue is the right edge, then skip to the right 2 times
            Similarly, if there is a 0 on the bottom area, then move down 2 times instead of 1
            
            How can we expand this logic further? 
            - For a 3x3, if the column is in the middle, then we want to move right 2x
            - If the column is on the right, then move right 3x
            
            The row or column index is determined by the (currentIndex - left) --> The spaces we want to move is index + 1
            
            Could we use a BFS-like thing to determine the largest square that we could fill?
            
            If we know that there is an element to our cell's right, up and top-right (or 3 directions relative to current), then a square of size 2 exists
            Top-left corner's top
            Bottom-right corner's right
            Top-right corner's 3?
            
            We need to repeat the top-left and bottom-right stuff, but adding 1 additional to the right/up each square size after 2
            
            Does a cell exist with a 1 on the top and right edges of a existing square?
            
            DFS/BFS type algorithm
            - Create a queue of all 1s within the matrix => O(m * n)
            - For each item, check to see on its right and top edge whether or not a 1 exists, and for the top-right corner, if a 1 exists in its top-right
            - Add all of the new cells into a running list, and increment some sort of running counter for side length
            - Recurse on that list of cells -> Returns an integer value representing the number of side lengths to add
                -> 2 lists as input (top, right), if top.size == right.size and not empty && corner exists (last value in both lists are the same), then recurse
                    -> Maybe if it's the last value on the top and the right?
                    -> If the top and right lists are empty by the time we reach the end, then the corner doesn't matter
        */
        var maxSideLength = 0
        //Ways to speed up
        //Maybe start at the bottom-left
        //If a value has been explored already, mark it off in another DS, disregard it the next time we encounter
        /*
            0111
            0111
            1111
            1100
            
            Maybe something about the min of the height/width as an upper limit --> Already handled this
            
            0111
            0111
            0111
            0110
        */
        matrix.forEachIndexed { rowIndex, row ->
            row.forEachIndexed { colIndex, value ->
                if(value == '1') {
                    val newCell = Cell(rowIndex, colIndex)
                    val sideLength = maximalSquare(matrix, listOf(newCell), listOf(newCell))
                    if(sideLength > maxSideLength) maxSideLength = sideLength
                }
            }
        }
        return maxSideLength * maxSideLength
}

*/

class Solution {

    /*
        The full solution utilizes DP. DP is pretty straightforward in this problem, because we have a set of overlapping subproblems.
        Whether or not we have a larger square now depends on 3 things:
            - What is our current row length?
            - What is our current column length?
            - Does a previous corner exist (i.e is there an older square to build upon?)
            
        Those 3 questions form the basis of our recurrence relation, and thus our DP setup.
        
        If our row, column or corner are not the same size, then we can only create a square up the minimum of their sizes + 1
        - Why? -> Because we of course can not build a bigger square if we do not have a full row/column which is greater than the previous square, and if the previous square is smaller than our row/column, we are missing tiles
        
        0001
        0111
        0111
        1111 -> Even though rows and columns of 4 in this corner, previous square can't support that many!
        
        1110
        1110
        1111
        1111 -> Even though previous square is 3x3 and bottom row is 4, column isn't (rotate example for row)!
        
        Furthermore, since we are going row by row, column by column, we can actually disregard the 2d part of our matrix in favor of a 1d array instead.
        
        In a given cell, the current value is the previous (before override) top, the value in the previous index is the previous column (after override), and the previous (pre-override) value of the previous index is the previous corner
    */
    fun maximalSquare(matrix: Array<CharArray>): Int {
        val cellValues = IntArray(matrix[0].size + 1) {0}
        var previousCorner = 0
        var maxLength = 0
        matrix.forEachIndexed { rowIndex, row ->
            row.forEachIndexed { colIndex, value ->
                val previousValue = cellValues[colIndex] //Capture the value before it's overridden - this is the previous corner
                if(value == '1') {
                    //The value at our cell is the minimum of the previous corner, column (current cell), row (prior cell) + 1
                    cellValues[colIndex] = minOf(previousCorner, cellValues.getOrNull(colIndex - 1) ?: 0, cellValues[colIndex]) + 1
                    if(cellValues[colIndex] > maxLength) maxLength = cellValues[colIndex]
                } else {
                    cellValues[colIndex] = 0
                }
                previousCorner = previousValue //Set the previousCorner to that value
            }
        }
        return maxLength * maxLength
    }
}