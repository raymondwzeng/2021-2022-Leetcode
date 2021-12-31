class Solution {
    public int majorityElement(int[] nums) {
        //Simple solution, use a hashmap and see when we end up crossing the nums/2 length mark.
        //Nonsimple solution, sort and count. Radix sort would be (maybe?) linear.
        Arrays.sort(nums); //Stupid mistake 4: Sorting after setting up base numbers. Lol
        int frequency = 1;
        int lastNum = nums[0]; //We don't need to worry about an empty array, given in problem.
        for(int i = 1; i < nums.length; i++) { //Error in first time submission: Starting at 0 (we already counted it!)
            if(nums[i] == lastNum) {
                frequency++;
                if(frequency > nums.length/2) return lastNum;
            } else {
                frequency = 1; //Error in second submission: Starting at 0...again. (Bruh)
                lastNum = nums[i];
            }
        }
        return lastNum; //Error in third submission: Using -1 as an error condition rather than the lastNum (as majority element will always exist.)
        //Possibly O(n) + O(n), potentially O(nlogn) + O(n)
    }
}