/*
        //Restrictions: Must run in O(n) time, and no division (why no division?).
        //A follow up: Must be O(1) space
        //Let's not worry about the O(1) space for now. Let's just try to think about the O(n) operation.
        //Well, the brute force solution, while working, would not be O(n).
        //It would simply move from 0..nums.length-1 and multiply, ignoring the number at i.
        //Thus, it would perform (n-1) work each layer, and so would have a runtime of O(n^2).
        
        //Example 2 insight: If we see 0, we can write a 0 to all elements except 0 (e.g. skip until 0).
        //Example 1 insight: If we could use division, we could simply run the operation to get the total product, and divide by each value.
        //Is there any property of numbers between -30 and 30 that we could abuse?
        //Or is there any sort of state we could accumulate?
        
        //Doesn't seem like a DP problem. There's no "state" to accumulate
        //We can't sort either - that would mess up the ordering of the numbers which is not what we want
        //"Prefix or suffix of nums". Hm...maybe we could memotize something actually?
        
        //Suppose we start at 1. The leading product is 1.
        //Move to 2. The last product is 1, * current number = 2.
        //Let's look at 3. The last product is 2, * curr = 6.
        //Look at 4. The last product is 6, * curr = 24.
        //Now look at the value at 3. We know that the numbers before is (2*1), whereas the numbers after is (4*3*2*1).
        //Basically, our problem is how to convert (4*3*2*1) with (2*1) = (4*2*1).
        //We have [1, 2*1, 3*2*1, 4*3*2*1]. How to turn that into (4*2*1)?
        //24-6+2 = 20, != 8.
        //What if we multiply up to that point from the sides?
        //So [1, 2, 3, 4] has an array of [1, 2*1, 3, 4] Basically, and 3 would simply be 2 * 4.
        //Wait a minute.
        //If we do 2, we multiply from the left & right. so it becomes [1, 2, 3*4, 4]. Then the value at 2 is simply 12*1, or 12.
        //But we still repeat work for all values. That won't work.
        //[1, 2, 3, 4]
        //[1, 2, 6, 24] or
        //[0, 1, 2, 6] -> Ignoring the current
        //[24, 12, 8, 6]
        //Throwing in the towel - Neetcode solution
*/

class Solution {
    public int[] productExceptSelf(int[] nums) {
        //The solution is sorta close to what I was thinking about.
        //We calculate the prefix and postfix of each area.
        //But in the more optimized solution, we simply keep a running count of the postfix as we run backwards, using output array as the prefix container.
        int[] result = new int[nums.length];
        int postFix = 1;
        int preFix = 1;
        for(int i = 0; i < nums.length; i++) {
            preFix *= nums[i];
            result[i] = preFix;
        }
        for(int j = nums.length - 1; j >= 0; j--) {
            if(j-1 == -1) {
                preFix = 1;
            } else { //Change the prefix to 1 if we're at the beginning. Otherwise, just get the previous value in the result.
                preFix = result[j-1];
            }
            result[j] = preFix * postFix;
            postFix *= nums[j];
        }
        return result;
    }
}