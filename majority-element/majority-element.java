class Solution {
    public int majorityElement(int[] nums) {
        //Standard solution: Use a hashtable lol
        //Edge case: 1 item in list (we're given that a majority element always exists)
        HashMap<Integer, Integer> freqMap = new HashMap<Integer, Integer>();
        for(int i = 0; i < nums.length; i++) {
            if(freqMap.get(nums[i]) == null) {
                freqMap.put(nums[i], 1);
            } else {
                freqMap.put(nums[i], freqMap.get(nums[i]) + 1);
            }
            if(freqMap.get(nums[i]) > nums.length/2) {
                return nums[i];
            } 
        }
        return -1;
    }
}