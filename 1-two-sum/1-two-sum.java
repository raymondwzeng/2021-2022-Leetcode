class Solution {
    public int[] twoSum(int[] nums, int target) {
        int[] output = new int[2]; //Returned array of 2 numbers.
        HashMap<Integer, Integer> foundSet = new HashMap<>();
        
        for(int i = 0; i < nums.length; i++) {
            if(foundSet.containsKey(target - nums[i])) {
                output[0] = i;
                output[1] = foundSet.get(target - nums[i]);
                return output;
            } else if(foundSet.containsKey(nums[i]) == false) { //Add to set if it doesn't exist.
                foundSet.put(nums[i], i);
            }
        }
        
        return output; //Weird thing at the end because Java moment
    }
}