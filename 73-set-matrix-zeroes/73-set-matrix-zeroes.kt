class Solution {
    fun setZeroes(matrix: Array<IntArray>): Unit {
        //DFS question
        //How do we tell whether a cell is an "original zero" compared to a "new zero"?
        /*
            Straightforward solution -> Create a copy of the matrix to keep track of where the 0s are, copy back into the matrix
            O(mn) space
            
            Dimensions: 200x200 maximum
            
            Start by iterating through the matrix and fill the row/column boolean array
            
            Each cell in the array will be true/false depending on if a zero exists on that row or column --> O(m + n) space
        */
        val rowArray = BooleanArray(matrix.size) {false}
        val colArray = BooleanArray(matrix[0].size) {false}
        
        //Iterate through the matrix, populating row/colArray
        matrix.forEachIndexed { rowIndex, row ->
            row.forEachIndexed { colIndex, value ->
                if(value == 0) {
                    rowArray[rowIndex] = true
                    colArray[colIndex] = true
                }
            }
        }
        
        //Interate through the matrix again, this time setting values if they are on the same row/column as a zero
        matrix.forEachIndexed { rowIndex, row ->
            row.forEachIndexed { colIndex, value ->
                if(rowArray[rowIndex] == true || colArray[colIndex] == true) matrix[rowIndex][colIndex] = 0
            }
        }
    }
}