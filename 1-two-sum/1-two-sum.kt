class Solution {
    fun twoSum(nums: IntArray, target: Int): IntArray {
        var set = mutableMapOf<Int, Int>()
        nums.forEachIndexed { index, num ->
            if(set.contains(target - num)) {
                return intArrayOf(index, set.get(target - num) as Int)
            }
            set.put(num, index);
        }
        return intArrayOf(-1)
    }
}