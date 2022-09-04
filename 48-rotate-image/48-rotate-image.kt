class Solution {
    fun rotate(matrix: Array<IntArray>): Unit {
        /*
            Hold a temporary variable that correponds to the replaced value
            For each index from 0 to n - 1 (exclusive)
                Replace the variable in our current cell into the cell that represents the image, rotated 90 degrees
            Move 1 layer inwards, so have the left bound and right bound (exclusive) shrink by 1
                Stop when we have the left and right bounds overlapping or crossing
                
            O(n^2) time
            
            123 -> Move item [0,0] to *[0,2], Move item [0, 2] to *[2,2], Move item [2,2] to *[2,0], Move item [2,0] to *[0,0]
            456 -> Move item [0,1] to *[1,2], Move item [1, 2] to *[2,1], Move item [2,1] to *[1,0], Move item [1,0] to *[0,1]
            789
            
            1234 -> Inner layer (where left = 1, right = 2)
            5678 -> Move item [1,1] to *[1,2], Move item [1,2] to *[2,2], Move item [2,2] to *[2,1], Move item [2,1] to *[1,1]
            9012
            3456
            
        */
        if(matrix.size == 1) return //Don't need to do anything for matrix size 1
        var leftBound = 0
        var rightBound = matrix.size - 1 //Exclusive
        while(leftBound < rightBound) { //Controlling for layers
            for(index in 0..rightBound - leftBound - 1) { //Should be renamed offset
                var previous = matrix[leftBound][leftBound + index]
                //1st swap
                var temp = matrix[leftBound + index][rightBound]
                matrix[leftBound + index][rightBound] = previous
                previous = temp
                //2nd swap
                temp = matrix[rightBound][rightBound - index]
                matrix[rightBound][rightBound - index] = previous
                previous = temp
                //3rd swap
                temp = matrix[rightBound - index][leftBound]
                matrix[rightBound - index][leftBound] = previous
                previous = temp
                //Final swap
                matrix[leftBound][leftBound + index] = previous
            }
            leftBound++
            rightBound--
        }
    }
}