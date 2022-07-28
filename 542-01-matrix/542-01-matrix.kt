class Solution {
    
    data class Cell(val column: Int, val row: Int)
    
    fun updateMatrix(mat: Array<IntArray>): Array<IntArray> {
        //Pretty good candidate for a BFS type thing
        /*
            For each column in mat
                For each row in column
                    If the column cell value is 0, immediately write the value in the result as 0
                    If the column cell value is not zero, we need to perform a BFS on that.
                        Add the cell into a queue
            (Discovering cells that need to be filled in)
            
            While the queue is not empty
                If the cell now has a nonzero value (because any cell that is in here will be nonzero), skip
                Otherwise, check all adjacent cells. 
                    If nonzero, check if the value in result is nonzero
                        If yes, then see if add 1 is less than current
                        If not, then add to queue.
                    If zero, set to 1
                
            You can choose to BFS from all zero cells, or BFS from all nonzero cells
            Both will be O(m*n*#) + O(m*n), where # = either the number of zero cells or nonzero cells
        */
        val returnArray = Array<IntArray>(mat.size) { index -> IntArray(mat[index].size) }
        val fillQueue = mutableListOf<Cell>()
        mat.forEachIndexed { column: Int, colReference: IntArray -> 
            colReference.forEachIndexed { row: Int, value: Int -> 
                if(value == 0) {
                    returnArray[column][row] = 0
                    fillQueue.add(Cell(column, row))
                }
            }
        }
        
        /*
        000    000    000    000    000    000
        011 -> 0__ -> 01_ -> 011 -> 011 -> 011
        111    ___    ___    ___    1__    1__
        
        
        111 ---  
        111 ---
        110 --0
        
        1. How do we terminate this condition with the BFS if we keep on adding adjacent cells?
            -> If we replace, then we add to the list (because we might proliferate it)
        */
        
        while(!fillQueue.isEmpty()) {
                val currentCell = fillQueue.first() //Of type Cell
                val currentDepth = returnArray[currentCell.column][currentCell.row]
                if(currentCell.column - 1 >= 0 && mat[currentCell.column - 1][currentCell.row] != 0) { //Nonzero top
                //Check to see if our current cell's value is less than our current depth + 1
                //If yes, replace, if not, don't
                    if(returnArray[currentCell.column - 1][currentCell.row] == 0 || currentDepth + 1 < returnArray[currentCell.column - 1][currentCell.row]) {
                        returnArray[currentCell.column - 1][currentCell.row] = currentDepth + 1
                        fillQueue.add(Cell(currentCell.column - 1, currentCell.row)) //"Flood fill"
                    }
                }
                if(currentCell.column + 1 < mat.size && mat[currentCell.column + 1][currentCell.row] != 0) { //Nonzero bottom
                //Check to see if our current cell's value is less than our current depth + 1
                //If yes, replace, if not, don't
                    if(returnArray[currentCell.column + 1][currentCell.row] == 0 || currentDepth + 1 < returnArray[currentCell.column + 1][currentCell.row]) {
                        returnArray[currentCell.column + 1][currentCell.row] = currentDepth + 1
                        fillQueue.add(Cell(currentCell.column + 1, currentCell.row))
                    }
                }
                if(currentCell.row - 1 >= 0 && mat[currentCell.column][currentCell.row - 1] != 0) { //Nonzero left
                //Check to see if our current cell's value is less than our current depth + 1
                //If yes, replace, if not, don't
                    if(returnArray[currentCell.column][currentCell.row - 1] == 0 || currentDepth + 1 < returnArray[currentCell.column][currentCell.row - 1]) {
                        returnArray[currentCell.column][currentCell.row - 1] = currentDepth + 1
                        fillQueue.add(Cell(currentCell.column, currentCell.row - 1))
                    }
                }
                if(currentCell.row + 1 < mat[currentCell.column].size && mat[currentCell.column][currentCell.row + 1] != 0) { //Nonzero right
                //Check to see if our current cell's value is less than our current depth + 1
                //If yes, replace, if not, don't
                    if(returnArray[currentCell.column][currentCell.row + 1] == 0 || currentDepth + 1 < returnArray[currentCell.column][currentCell.row + 1]) {
                        returnArray[currentCell.column][currentCell.row + 1] = currentDepth + 1
                        fillQueue.add(Cell(currentCell.column, currentCell.row + 1))
                    }
                }
            
            fillQueue.removeAt(0)
        }
        
        
        return returnArray
    }
}