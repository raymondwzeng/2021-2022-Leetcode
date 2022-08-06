class Solution {
    /*
        1, 2, 3
        
        [1] -> permute on index 1
            -> [1, 2]
            -> [1, 3]
        [2] -> permute on index 2
            -> [2, 3]
            -> [2, 1]
        [3] -> permute on index 0
            -> [3, 1]
            -> [3, 2]
            
        //We can start at the value, however, we don't want to duplicate it.
    */
    //O(2^n * n^2)
    fun permute(nums: IntArray, list: List<Int>): List<List<Int>> {
        val resultList = mutableListOf<List<Int>>()
        for(i in 0..nums.size - 1) {
            if(!list.contains(nums[i])) {
                val copy = list.toMutableList()
                copy.add(nums[i])
                if(copy.size == nums.size) {
                    resultList.add(copy)
                } else {
                    val recursiveResult = permute(nums, copy)
                    if(recursiveResult.isNotEmpty()) {
                        recursiveResult.forEach { item -> resultList.add(item) }
                    }
                }
            }
        }
        return resultList
    }
    
    fun permute(nums: IntArray): List<List<Int>> {
        //Potentially a backtracking problem
        /*
            Choice: List, with the same size as the original list
            Constraint: The list must be unique
            Convergance: When our list is essentially "complete" or full -> MutableList, and up until size == nums.size
        */
        return permute(nums, mutableListOf<Int>())
    }
}