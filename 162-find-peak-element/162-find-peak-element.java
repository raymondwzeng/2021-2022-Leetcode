class Solution {
    public int findInternalPeak(int[] nums, int left, int right) {
        int middleIndex = (left+right)/2;
        int leftValue = middleIndex - 1 < 0 ? Integer.MIN_VALUE : nums[middleIndex-1];
        int rightValue = middleIndex + 1 >= nums.length ? Integer.MIN_VALUE : nums[middleIndex + 1];
        if(nums[middleIndex] >= leftValue && nums[middleIndex] >= rightValue) return middleIndex; //Edge case: Integer.min. In this case, since we're given the fact that values next to each other wil not be the same, then the only time this will be true is if this -Integer.MIN_VALUE of 1 occurs.
        if(leftValue < rightValue) {
            return findInternalPeak(nums, middleIndex+1, right); //Gravitate towards the higher number
        } else {
            return findInternalPeak(nums, left, middleIndex);
        }
    }
    
    public int findPeakElement(int[] nums) {
        //Probably a sliding window problem?
        //Actually, if it needs to run in O(logn), that complicates things by quite a bit.
        //We only need to find one peak
        //Of course we can't sort, it won't be fast enough.
        
        
        //Additional givens:
        //All values next to each other are unique.
        //Border elements already have 1/2 sides fulfilled, as the runoff is -inf
        
        //What actually runs in O(logn)? Binary search, that's the only one I can think of.
        //Let's see...we'll probably trade away space for time then in this case.
        //We can't run binary search as the index is not sorted.
        //A map/set dynamic programming thing seems plausible, but how?
        return findInternalPeak(nums, 0, nums.length - 1);
        //A sort of "botched binary search" as I'll call it, where we divide the search in half. We gravitate towards the higher number as there is
        //a higher chance of that higher value being the peak
        //This runs in O(logn) time because of the fact that our search distance is halved each time.
        //This *feels* a bit like a greedy algo, maybe not, but perhaps there's a situation where this doesn't quite work?
    }
}