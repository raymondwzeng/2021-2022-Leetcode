/**
//Map method, linear time and space
val frequencyMap = mutableMapOf<Int, Int>()
        nums.forEach { num -> 
            if(frequencyMap.contains(num)) {
                frequencyMap.put(num, frequencyMap.get(num)!! + 1)
            } else {
                frequencyMap.put(num, 1)
            }
            if(frequencyMap.get(num)!! > nums.size / 2) return num
        }
        return -1
*/

class Solution {
    fun majorityElement(nums: IntArray): Int {
      //The straightforward approach is to use a map and count up from there.
        //But how could we solve this in linear time and constant space?
        //Assuming that the sort method uses a linear time sort like radix, then we could iterate over the elements and then count up.
        //Since we assume the array always has a majority element, then we don't need to handle empty case.
        nums.sortDescending()
        var currentNum = nums.get(0)
        var currentNumFrequency = 1
        for(i in 1..nums.size-1) {
            if(nums.get(i) == currentNum) {
                currentNumFrequency++
                if(currentNumFrequency > nums.size/2) return currentNum
            } else {
                currentNum = nums.get(i)
                currentNumFrequency = 1
            }
        }
        return currentNum
    }
}