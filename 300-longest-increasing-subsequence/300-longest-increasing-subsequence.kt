/* First approach, dynamic programming. Solved, but average runtime. Probably acceptable for interviews.
        /*
            Traditionally, this is solved with dynamic programming
            -> Each element has two subproblems -> We either start the sequence at our current number (1), or we continue our subsequence from a previous number (for all elements prior?)
            Comparison would be if the number itself is < current number, and chainSize (number) > max so far
            -> O(n^2) runtime
            
            What if we wanted to do it in O(nlogn) time?
                -> We don't want to change the order of the remaining elements, so no sorting at all.
                -> Kth-style approach with quickselect is out
                -> Maybe some sort of binary tree?
        
            Suppose that this is a union-find problem
                - Start each element as its own chain 
                - For each element within the chain, we want to maximize the length
                Store 3 variables within the node: (parent: Node, value: Int, min: Int)
                The point of the min is to see if a candidate node can fit as the last node within our group
                
                If we wanted to add an element/merge 2 components together
                1. Compare the roots to see which one would merge into which
                2. See if we can even fit the elements -> Set of nodes in the root, and check the intersection?
                    -> Suppose we had 2 components with roots a and b. If a < b, and a.set intersect b.set == null, then we know that
                    for each element within a, no element exists as a descendant of b, and furthermore, since there is no overlap, and a is the largest element within a.set, then all elements would be able to fit within b.set.
                3. Set a's parent node to b, and set b.set to a.set union b.set
                3a. If the nodes are not compressed, we need to do so (in order to have a faster runtime)
        */
        val longestChainArray = IntArray(nums.size) { 1 }
        var maxChainSeenTotal = 1
        nums.forEachIndexed { index, num ->
            var maxChainSoFar = 1
            for(i in 0..index - 1) {
                if(nums[i] < num && longestChainArray[i] + 1 > maxChainSoFar) maxChainSoFar = longestChainArray[i] + 1
            }
            longestChainArray[index] = maxChainSoFar
            if(maxChainSoFar > maxChainSeenTotal) maxChainSeenTotal = maxChainSoFar
        }
        return maxChainSeenTotal
*/

class Solution {
    fun lengthOfLIS(nums: IntArray): Int {
        val piles = mutableListOf<Int>()
        nums.forEach { num ->
            var index = piles.binarySearch(num)
            if(index < 0) index = index.inv() //Inverse of (-(insertionIndex) - 1) == insertionIndex
            if(index == piles.size) piles.add(num) else piles.set(index, num)
        }
        return piles.size
    }
}