class Solution {
    data class Cell(val row: Int, val col: Int, var canFlowPacific: Boolean, var canFlowAtlantic: Boolean)
    
    fun updateCell(origin: Cell, destination: Cell): Boolean { //Updates a cell, and returns a boolean on whether or not it was updated
        var wasUpdated = false
        if(origin.canFlowPacific == true && destination.canFlowPacific == false) {
            destination.canFlowPacific = true
            wasUpdated = true
        }
        if(origin.canFlowAtlantic == true && destination.canFlowAtlantic == false) {
            destination.canFlowAtlantic = true
            wasUpdated = true
        }
        return wasUpdated
    }
    
    fun checkFlow(cellQueue: Queue<Cell>, matrix: Array<Array<Cell>>, heights: Array<IntArray>) {
        //BFS in 4 cardinal directions to see if we can reach some sort of flow
        while(!cellQueue.isEmpty()) {
         val front = cellQueue.poll()
            if(front.row - 1 >= 0 && heights[front.row - 1][front.col] >= heights[front.row][front.col]) {
                if(updateCell(matrix[front.row][front.col], matrix[front.row - 1][front.col]) == true) {
                    cellQueue.add(matrix[front.row - 1][front.col])
                }
            }
            if(front.row + 1 < matrix.size && heights[front.row + 1][front.col] >= heights[front.row][front.col]) {
                if(updateCell(matrix[front.row][front.col], matrix[front.row + 1][front.col]) == true) {
                    cellQueue.add(matrix[front.row + 1][front.col])
                }
            }
            if(front.col - 1 >= 0 && heights[front.row][front.col - 1] >= heights[front.row][front.col]) {
                if(updateCell(matrix[front.row][front.col], matrix[front.row][front.col - 1]) == true) {
                    cellQueue.add(matrix[front.row][front.col - 1])
                }
            }
            if(front.col + 1 < matrix[0].size && heights[front.row][front.col + 1] >= heights[front.row][front.col]) {
                if(updateCell(matrix[front.row][front.col], matrix[front.row][front.col + 1]) == true) {
                    cellQueue.add(matrix[front.row][front.col + 1])
                }
            }   
        }
    }
    
    fun pacificAtlantic(heights: Array<IntArray>): List<List<Int>> {
        //BFS
        //m, n <= 200 -> O(mn)-ish runtime
        //Probably run BFS until we reach a that is either adjacent to the oceans, or that we know can reach a given ocean
        
        //O(m*n) operation to recreate the tiles as our cells, before running BFS on the tiles
        val queue = LinkedList<Cell>()
        val matrix = Array<Array<Cell>>(heights.size) { row -> Array<Cell>(heights[0].size){ column ->
                val newCell = Cell(row, column, false, false)
                if (newCell.row == 0 || newCell.col == 0) newCell.canFlowPacific = true
                if (newCell.row == heights.size - 1 || newCell.col == heights[0].size - 1) newCell.canFlowAtlantic = true
                if(newCell.canFlowPacific == true || newCell.canFlowAtlantic == true) {
                     queue.add(newCell)
                }
                newCell
            }
        }
        
       val returnList = mutableListOf<List<Int>>()
       checkFlow(queue, matrix, heights) //BFS call to propogate the canFlows as far as they can
        
       matrix.forEach { row ->
           row.forEach { cell ->
               if(cell.canFlowPacific == true && cell.canFlowAtlantic == true) returnList.add(listOf(cell.row, cell.col))
           }
       }
       
       return returnList
    }
}