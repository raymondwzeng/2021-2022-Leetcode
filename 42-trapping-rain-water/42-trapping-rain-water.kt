class Solution {
    fun trap(height: IntArray): Int {
        /* n <= 2 * 10^4, height[i] <= 10^5. Not viable to do m * n, maybe m + n may work
            A few rules:
                - If an empty space has a block on its left and right (at some point), we're good to add 1
                - # Units is the min height between left and right - the middle height
                
                Problem: How do deal with the non-composite shapes ([2, 1, 0, 1, 3])
                We can view the volume that they take up as min height of both, minus the max height of the middle?
                
            Does a solution from container with most water help here?
            0. Move the left, right until there exists an edge, any water that is not within *a* structure will never hold.
            1. Start at each end, calculating the water volume as (right - left) * min(rightHeight, leftHeight)
            2. Move the left towards the right.
                -> If there is an edge which is taller, remove min(rightHeight, originalLeftHeight) from the volume (that space can not hold water)
                -> If there is an edge which is shorter, remove min(rightHeight, originalLeftHeight) - leftHeight (a portion of the space can not hold water)
                -> Set the new water amount to the current amount (volume -= (right - left) * min(rightHeight, originalLeftHeight), then volume += (right - left) * min(rightHeight, leftHeight))
                
                This could result in an O(n) runtime
        */
        if(height.size < 3) return 0
        var left = 0
        var right = height.size - 1
        while(height[left] == 0) left++
        while(height[right] == 0) right-- //Move until we have an actual edge
        var volume = 0
        var maxHeightSoFar = 0
        
        while(left < right) { //We're going to calculate until we bump into each other or cross
            if(maxHeightSoFar < minOf(height[left], height[right])) maxHeightSoFar = minOf(height[left], height[right])
            volume += maxHeightSoFar
            volume -= minOf(height[left], height[right])
            if(height[left] < height[right]) {
                left++
            } else {
                right--
            }
        }
        return volume
    }
}