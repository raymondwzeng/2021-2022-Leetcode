class Solution {
    fun sortColors(nums: IntArray): Unit {
        //One pass
        if(nums.size == 1) return
        var redPartition = 0
        var whitePartition = 0 //whitePartition will also serve as our indexer
        var bluePartition = nums.size - 1
        while(whitePartition <= bluePartition) {
            if(nums[whitePartition] == 0) {
                nums[whitePartition] = nums[redPartition] //Build up the red partition by swapping, after every call.
                nums[redPartition] = 0
                redPartition++
            }
            if(nums[whitePartition] == 2) {
                nums[whitePartition] = nums[bluePartition] //Build up the blue partition by swapping, after every call.
                nums[bluePartition] = 2
                bluePartition--
                //Since we are swapping to our current item and incrementing forward, we don't know what that item is, and therefore should decrement to consider it
                whitePartition--
            }
            whitePartition++
        }
    }
}