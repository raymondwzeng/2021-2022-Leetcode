class Solution {
    fun numIslands(grid: Array<CharArray>): Int {
        //m x n grid
        //Return the number of islands that exist within that grid
        //Condition: The edges represent water
        
        //BFS or DFS when we encounter an island
        //If we haven't seen this island before, then we should denote it and fill in all of the spaces that it contains
        //If we have seen it before, then ignore it.
        
        //Copy over the grid, but if a cell is land, mark it initially as -1 (unexplored tiles)
        //Iterating over our new grid, if we encounter an unexplored tile, perform "Flood Fill" on that chain of land
            //Flood fill will use a number, which is then incremented.
        
        //O(m * n) initial iteration/copy
        //O(m * n) iterate through all cells within our new matrix + O(m * n) for BFS
        //Algorithm probably runs within the O(m * n) range, where m and n are the dimensions of the matrix
        //O(m * n) space complexity
        
        /*
        1 1 0 1 <- missed if we skip our iteration to the end of our BFS
        1 1 1 0
        0 0 0 0
        */
        
        data class Cell(val column: Int, val row: Int){}
        
        val islandGrid = MutableList<MutableList<Int>>(grid.size) { column -> MutableList<Int>(grid[column].size) { row ->
                if(grid[column][row] == '0') {
                    0
                } else {
                    -1
                }
            }
        }
        
        val queue = mutableListOf<Cell>()
        var accumulator = 0
        
        
        islandGrid.forEachIndexed { column, innerList ->
            innerList.forEachIndexed { row, value ->
                if(value == -1) {
                    //Add to BFS list
                    // println("Found new island at $column, $row")
                    queue.add(Cell(column, row))
                    accumulator++
                }
                //Flood-filling the connected pieces of land to our current island
                while(queue.isNotEmpty()) {
                    val front = queue.first()
                    if(islandGrid[front.column][front.row] == -1) {    
                        islandGrid[front.column][front.row] = accumulator
                        if(front.column - 1 >= 0 && islandGrid[front.column - 1][front.row] == -1) {
                            // println("Found connected land at ${front.column - 1}, ${front.row}")
                            queue.add(Cell(front.column - 1, front.row))
                        }
                        if(front.column + 1 < islandGrid.size && islandGrid[front.column + 1][front.row] == -1) {
                            // println("Found connected land at ${front.column + 1}, ${front.row}")
                            queue.add(Cell(front.column + 1, front.row))
                        }
                        if(front.row - 1 >= 0 && islandGrid[front.column][front.row - 1] == -1) {
                            // println("Found connected land at ${front.column}, ${front.row - 1}")
                            queue.add(Cell(front.column, front.row - 1))
                        }
                        if(front.row + 1 < islandGrid[front.column].size && islandGrid[front.column][front.row + 1] == -1) {
                            // println("Found connected land at ${front.column}, ${front.row + 1}")
                            queue.add(Cell(front.column, front.row + 1))
                        }
                    }
                    queue.removeAt(0)
                }
            }
        }
        
        return accumulator
    }
}