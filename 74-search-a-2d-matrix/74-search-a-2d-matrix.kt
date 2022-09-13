class Solution {
    
    /*
        Searches for the proper row in the matrix, inclusive of up and exclusive of down
    */
    fun searchRow(matrix: Array<IntArray>, target: Int, up: Int, down: Int): Int {
        val middleRow = (up + down) / 2
        return when {
           up > down -> -1
           matrix[middleRow][0] == target -> middleRow
           matrix[middleRow][0] < target -> {
               if((middleRow + 1 < matrix.size && matrix[middleRow + 1][0] > target) || (middleRow + 1 >= matrix.size)) return middleRow
               return searchRow(matrix, target, middleRow + 1, down)
           }
           else -> { //Current is greater
               if(middleRow - 1 >= 0 && matrix[middleRow - 1][0] < target) return middleRow - 1
               if(middleRow == 0) return -1 //Return -1 if we are at the first number in the matrix and STILL larger than target
               return searchRow(matrix, target, up, middleRow)
           }
        }
    }
    
    fun searchMatrix(matrix: Array<IntArray>, target: Int): Boolean {
        /*
            2d binary search O(logm) + O(logn)
            
            - Start by performing essentially binary search on the first item of each row
            -> We want to find the row with the greatest value without going over
                - If no such row is found, then return false
            - Perform binary search on the row itself -> Collections, return false if the resulting number is either greater than the size of the array or less than 0
            
            *Matrix is in [row][column] order
        */
        //Binary search on each first value of every row
        val row = searchRow(matrix, target, 0, matrix.size)
        if(row < 0 || row >= matrix.size) return false //If we could not find a suitable row to search, return immediately
        if(matrix[row][0] == target) return true //In case we get lucky
        val index = matrix[row].binarySearch(target) //Continue searching the row itself
        return (index >= 0 && index < matrix[row].size)
    }
}