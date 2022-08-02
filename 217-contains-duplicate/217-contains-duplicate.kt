class Solution {
    fun containsDuplicate(nums: IntArray): Boolean {
        //One possible solution -> Use a set, and if it exists within the set already, return true
        //Alternatively, we could also run a sort and check the elements.
        //We probably shouldn't use a set in this case because the values are a bit large in rango, but maybe that's not a huge deal.
        val numberSet = mutableSetOf<Int>()
        nums.forEach { num ->
            if(numberSet.contains(num)) {
                return true
            } else {
                numberSet.add(num)
            }
        }
        return false
    }
}