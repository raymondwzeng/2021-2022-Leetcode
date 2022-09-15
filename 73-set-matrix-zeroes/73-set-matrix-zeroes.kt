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
        var firstColumnHasZero = false
        var firstRowHasZero = false
        
        //Iterate through the matrix, populating row/colArray
        matrix.forEachIndexed { rowIndex, row ->
            row.forEachIndexed { colIndex, value ->
                if(value == 0) {
                    if(colIndex == 0) firstColumnHasZero = true
                    if(rowIndex == 0) firstRowHasZero = true
                    matrix[rowIndex][0] = 0
                    matrix[0][colIndex] = 0
                }
            }
        }
        
        //Interate through the matrix again, this time setting values if they are on the same row/column as a zero
        matrix.forEachIndexed { rowIndex, row ->
            row.forEachIndexed { colIndex, value ->
                if(rowIndex > 0 && colIndex > 0) {
                    if(matrix[rowIndex][0] == 0 || matrix[0][colIndex] == 0) matrix[rowIndex][colIndex] = 0
                }
            }
        }
        if(firstRowHasZero == true) { //We don't know if any 0 in the first row/col is a flag or not, so keep a boolean and fill spaces.
            matrix[0].forEachIndexed { colIndex, value ->  matrix[0][colIndex] = 0 }
        }
        if(firstColumnHasZero == true) {
            matrix.forEachIndexed { rowIndex, row -> row[0] = 0 }
        }
    }
}