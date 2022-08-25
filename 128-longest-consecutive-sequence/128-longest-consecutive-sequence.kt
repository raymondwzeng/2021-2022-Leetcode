class Solution {
    fun longestConsecutive(nums: IntArray): Int {
        /*
            Feels like a hashmap problem
            
            With O(n) runtime, sorting is out
            
            Iterate over the array in a first pass
                -> Keep track of the maximum value we've encountered so far
                -> Add all of the items into a hashmap, keeping track of the length of the sequence (initialize to 0)
                    - If a value exists which is 1 higher or 1 lower than the current value, our sequence length at that is equal to the length of the previous + 1 + if the other chain exists, the new sequence length
                    - Might need to update the nearest neighbors as well
            
            For all items within the map
                -> Find the max value
                
            [100, 4, 200, 1, 3, 2]
            Map:
            100: 1
            4: 1
            200: 1
            1: 1
            3: 2
            2: 4
            
            [0, 3, 7, 2, 5, 8, 4, 6, 0, 1]
            0: 1    0: 1
            3: 1    3: 1 -> 2 -> 4
            7: 1    7: 1 -> 2 -> 7
            2: 2    2: 1 -> 2
            5: 1    5: 1 -> 4 -> 7   
            8: 2    8: 1 -> 2
            4: 3    4: 1 -> 4
            6: 3    6: 1 -> 7
            1: 3    1: 1 -> Problem: Connecting to 0 and 2, with 2 never having updated with the new length
            
            Going back to set idea
            
            For each item in the set
                -> Check if it is the minimum -> If the item below it does not exist, then we know that it is the bottom of a chain
                -> Then for each bottom value in the chain, search up until we find the end
            O(n) runtime, O(n) space
        */
        val numberSet = mutableSetOf<Int>()
        nums.forEach {num -> numberSet.add(num) }
        var longestChain = 0
        numberSet.forEach { num ->
            if(!numberSet.contains(num - 1)) { //This is the bottom of the consecutive sequence
                var currentNum = num
                var sequenceLength = 1
                while(numberSet.contains(++currentNum)) sequenceLength++
                if(sequenceLength > longestChain) longestChain = sequenceLength
            }
        }
        return longestChain
    }
}