class Solution {
    fun twoSum(nums: IntArray, target: Int): IntArray {
        //Approach: Create a map of seen numbers.
        val seen = mutableMapOf<Number, Integer>()
        var result = IntArray(2)
        nums.forEachIndexed{ index, num -> 
            if(seen.containsKey(target - num)) {
                result[0] = index
                result[1] = seen.get(target - num) as Int //Kind of ugly. Maybe theres a better way?
                return result
            } else {
                seen.put(num, index as Integer) //Kind of ugly. Maybe theres a better way?
            }
        }
        return result
    }
}