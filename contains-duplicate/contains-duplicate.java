class Solution {
    public boolean containsDuplicate(int[] nums) {
        HashMap<Integer, Boolean> myMap = new HashMap<>();
        for(int i = 0; i < nums.length; i++)
        {
            if(myMap.get(nums[i]) != null)
            {
                return true;
            } else {
                myMap.put(nums[i], true);
            }
        }
        return false;
    }
}