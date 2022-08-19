class Solution {
    fun isValidSudoku(board: Array<CharArray>): Boolean {
        /*
            Only need to check the filled cells, and make sure that the configuration is valid
            
            Check if the row contains the same number
            Check if the column contains the same number
            Check if the 3x3 grid contains the same number
        
            Array (size 9) of rowSets -> For each row, check if the row already has an existing number, if yes, return false
            Array (size 9) of colSets -> Same as row, but for columns
            
            O(n x m)
            
            We could check each 3x3 manually -> Create a gridSet
            
            O(n x m)
        */
        val rowSetArray = Array<MutableSet<Int>>(9) { mutableSetOf<Int>() }
        val colSetArray = Array<MutableSet<Int>>(9) { mutableSetOf<Int>() }
        
        board.forEachIndexed { row, rowList -> //Fill up the setArrays
            rowList.forEachIndexed { col, value ->
                if(value != '.') {
                    val valAsInt = Integer.parseInt(value.toString())
                    if(rowSetArray[row].contains(valAsInt)) return false
                    if(colSetArray[col].contains(valAsInt)) return false
                    rowSetArray[row].add(valAsInt)
                    colSetArray[col].add(valAsInt)
                }
            }
        }
        
        var rowGrid = 0
        var colGrid = 0
        while(rowGrid < 3 && colGrid < 3) {
            val gridSet = mutableSetOf<Int>()
            for(rowOffset in 0..2) {
                for(colOffset in 0..2) {
                    val cell = board[(rowGrid * 3) + rowOffset][(colGrid * 3) + colOffset]
                    if(cell != '.') {
                        val value = Integer.parseInt(cell.toString())
                        if(gridSet.contains(value)) return false
                        gridSet.add(value)
                    }
                }
            }
            colGrid++
            if(colGrid == 3) {
                rowGrid++
                colGrid = 0
            }
        }
        
        return true
    }
}