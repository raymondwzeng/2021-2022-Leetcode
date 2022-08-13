class Solution {
    fun maxArea(height: IntArray): Int {
        //The area between any two (i, j) will be minOf(height[i], height[j]) * (j - i)
        //# of inputs -> 2 and 10^5
        
        /*
            Brute force
                For each element in height -> O(n)
                    For each element beyond the current -> (n - 1), then O(n - 2)
                        Calculate the area, and store a running maximum
                Return the maximum
                
            O(n^2) time, where n is the number of heights in our array 
        
            What can we do to improve the potential runtime?
            - Sorting? No -> That would mess up the input
            
            There's no ordering in the inherent height set that would allow us to greedily take a swing at the problem
            
            O(n) is probably possible given the size of inputs possible, O(logn) probably not
            
            [1, 8*, 6, 2, 5, 4, 8, 3, 7*] => Area is min(8, 7) * (8 - 1) = 49, change such that we acheieve a number >= 49
            -> Move the smaller number inwards, maybe?
            
            1 * 8 = 8 (new max)
            7 * 7 = 49 (new max)
            3 * 6 = 18
            8 * 5 = 40 -> Should we move both if they are equal?
            4 * 4 = 16
            ..
            
            O(n) runtime
        */
        
        var left = 0
        var right = height.size - 1
        var max = 0
        
        while(left < right) {
            val currentArea = minOf(height[left], height[right]) * (right - left)
            if(currentArea > max) max = currentArea
            if(height[left] < height[right]) {
                left++
            } else {
                right--
            }
        }
        
        return max
    }
}