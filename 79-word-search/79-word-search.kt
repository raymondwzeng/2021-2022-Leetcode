class Solution {
    data class Coordinate(val row: Int, val column: Int)
    
    fun search(board: Array<CharArray>, word: String, index: Int, currentCell: Coordinate, ignoreList: MutableSet<Coordinate>): Boolean {
        var up = false
        var down = false
        var left = false
        var right = false
        // println("Coordinate: $currentCell, ignoreList: $ignoreList")
        if(currentCell.row - 1 >= 0) {
            val upCoordinate = Coordinate(currentCell.row - 1, currentCell.column)
            if(board[upCoordinate.row][upCoordinate.column] == word[index + 1] && !ignoreList.contains(upCoordinate)) {
                if(index + 1 == word.length - 1) return true
                ignoreList.add(currentCell)
                up = search(board, word, index + 1, upCoordinate, ignoreList)
                ignoreList.remove(currentCell)
            }
        } 
        if (currentCell.row + 1 < board.size) {
            val downCoordinate = Coordinate(currentCell.row + 1, currentCell.column)
            if(board[downCoordinate.row][downCoordinate.column] == word[index + 1] && !ignoreList.contains(downCoordinate)) {
                if(index + 1 == word.length - 1) return true
                ignoreList.add(currentCell)
                down = search(board, word, index + 1, downCoordinate, ignoreList)
                ignoreList.remove(currentCell)
            }
        }
        if(currentCell.column - 1 >= 0) {
            val leftCoordinate = Coordinate(currentCell.row, currentCell.column - 1)
            if(board[leftCoordinate.row][leftCoordinate.column] == word[index + 1] && !ignoreList.contains(leftCoordinate)) {
                if(index + 1 == word.length - 1) return true
                ignoreList.add(currentCell)
                left = search(board, word, index + 1, leftCoordinate, ignoreList)
                ignoreList.remove(currentCell)
            }
        }
        if(currentCell.column + 1 < board[currentCell.row].size) {
            val rightCoordinate = Coordinate(currentCell.row, currentCell.column + 1)
            if(board[currentCell.row][currentCell.column + 1] == word[index + 1] && !ignoreList.contains(rightCoordinate)) {
                if(index + 1 == word.length - 1) return true
                ignoreList.add(currentCell)
                right = search(board, word, index + 1, rightCoordinate, ignoreList)
                ignoreList.remove(currentCell)
            }
        }
        return up || down || left || right
    }
    
    fun exist(board: Array<CharArray>, word: String): Boolean {
        //Graph problem
        /*
            Consider each letter as a node, and each adjacent set of nodes to have an edge (unweighed, undirected) between them.
            
            Question: Does a specific path exist within the graph?
            
            DFS would work, BFS too, but it might be slower? Not sure
            
            Problem: We can not reuse a cell more than once, per try -> Some sort of data structure/storage
            
            Set in Kotlin remembers the order in which elements are added
            
            Algorithm
            -> Iterate over all elements within the matrix O(mn)
                - Keep a list of all possible entrypoints (word[0])
            -> Run a subroutine over all possible entrypoints
            
            Subroutine
            - Check in the 4 cardinal directions whether the next item exists.
                - If found, add to a set of coordinates that we have searched, and recurse on that element.
            - Finally, remove that element from our set of searched coordinates, and return
            
            Search pruning -> ??
        */
        val entryPoints = mutableListOf<Coordinate>()
        
        board.forEachIndexed { rowIndex, row ->
            row.forEachIndexed { columnIndex, cell ->
                if(cell == word[0]) {
                    if(word.length == 1) return true
                    entryPoints.add(Coordinate(rowIndex, columnIndex))
                }
            }
        }
        
        entryPoints.forEach { point -> if(search(board, word, 0, point, mutableSetOf()) == true) return true}
        return false
    }
}