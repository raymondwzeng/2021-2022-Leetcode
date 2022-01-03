class Solution {
    public int majorityElement(int[] nums) {
        //Start by sorting, because that's possible.
        //Radix sort is possibly O(n) time, but even merge is O(nlogn)
        Arrays.sort(nums);
        int lastValue = nums[0];
        int quantity = 1;
        for(int i = 1; i < nums.length; i++) { //this step is O(n) time, thus our total is O(nlogn) + O(n) at worst, or O(nlogn) time.
            if(nums[i] == lastValue) {
                quantity++;
                if(quantity > nums.length/2) return lastValue;
            } else {
                lastValue = nums[i];
                quantity = 1;
            }
        }
        return lastValue;
    }
}