class Solution {
    fun uniquePaths(m: Int, n: Int): Int {
        //"Generate all possible unique paths to the finish line"
        /*
            m -> Rows
            n -> Columns
            
            For each cell that the robot can traverse, it can take one of two choices:
            - Go right
            - Go down
            - Except for the edges (right edge and bottom edge), where it can only move in one direction
            
            Constraint -> Robot must travel to a valid square -> row < m, col < n
            Explore on each possible iteration the possibilities of that particular cell
            Increment on the number of possibilities
            Return when we reach the bottom cell.
            
            O(2^(mn)) runtime, there must be a better way
            
            Subproblem: uniquePaths(m, n) = uniquePaths(m - 1, n - 1) + 2
            In terms of m == 1 || n == 1, return 1
            
            [R][][]
            [][][*]
            
            RRD
            DRR
            RDR
            
            0 1 2 3 4 5 6 7 -> n
        0   0 0 0 0 0 0 0 0   
        1   0 1 1 1 1 1 1 1
        2   0 1 2 3 4 5 6 7
        3   0 1 3 6 10 15 21 28
        
        m
        
        Rather than taking the corner, do we add the number of items from m-1, and n-1 recursions? -> Choice between going right or down
            1 <= m, n <= 100
            O(mn) time, with the same amount of space
        */
        if(m == 1 || n == 1) return 1
        val dp = Array<IntArray>(m) { IntArray(n){1} } //You need an extra row and column because of 0 indexing, you buffoon >:(
        dp.forEachIndexed { rowIndex, row ->
            row.forEachIndexed { colIndex, value ->
                dp[rowIndex][colIndex] = when {
                    rowIndex == 0 || colIndex == 0 -> 1
                    else -> dp[rowIndex][colIndex - 1] + dp[rowIndex - 1][colIndex]
                }
            }
        }
        return dp[m - 1][n - 1]
    }
}