/* Close attempt.
var leftEnd = 0
        var rightEnd = matrix.size - 1
        var topEnd = 0
        var bottomEnd = matrix[0].size - 1
        //Row - Column order
        //Right -> Down -> Left -> Up = 1 cycle, repeat until you reach some base condition
        //[0, 0][0, 1][0, 2][1, 2][2, 2][2, 1][2, 0][1, 0][1, 1]   
        var column = 0
        var currentRow = 0
        for(col in columnLimitLeft + 1..columnLimitRight - 1) { //Right
            print(matrix[0][col])
            column = col
        } 
        columnLimitLeft++
        println()
        //+ 1 when going down, -1 when going up
        for(row in rowLimitUp + 1..rowLimitDown - 1) { //Down
            print(matrix[row][column])
            currentRow = row
        }
        rowLimitUp++
        println()
        for(col in columnLimitRight - 1 downTo columnLimitLeft) { //Maybe not downTo 0, because we'll bump into stuff later
            print(matrix[currentRow][col])
            column = col
        }
        columnLimitRight--
        println()
        for(row in rowLimitDown - 1 downTo rowLimitUp) { //Again, maybe not 0
            print(matrix[row][column])
            currentRow = row
        }
        rowLimitDown--
        println()
        return listOf<Int>()
*/

class Solution {
    fun spiralOrder(matrix: Array<IntArray>): List<Int> {
        //[Row][Column] order
        //Each time we traverse a row or column, "remove" it from the possible traversal points with the end points.
        var leftEnd = 0
        var rightEnd = matrix[0].size - 1
        var topEnd = 0
        var bottomEnd = matrix.size - 1
        
        var returnList = mutableListOf<Int>()
        
        while(leftEnd <= rightEnd && topEnd <= bottomEnd) {
            for(column in leftEnd..rightEnd) returnList.add(matrix[topEnd][column]) //Finish traversing top, remove the top edge.
            topEnd++
            for(row in topEnd..bottomEnd) returnList.add(matrix[row][rightEnd]) //Finish traversing right, remove right edge.
            rightEnd--
            if(rightEnd < leftEnd || bottomEnd < topEnd) break //Additional sanity check. See example 2 - our end position after traversing top or right edge won't cause a check until later, leading to duplicate values.
            for(column in rightEnd downTo leftEnd) returnList.add(matrix[bottomEnd][column]) //Finish traversing bottom, remove bottom edge.
            bottomEnd--
            for(row in bottomEnd downTo topEnd) returnList.add(matrix[row][leftEnd]) //Finish traversing left, remove left edge.
            leftEnd++
        }
        return returnList
    }
}