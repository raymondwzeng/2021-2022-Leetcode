class Solution {
    public void merge(int[] nums1, int m, int[] nums2, int n) {
        //Ok, so we want to probably use 2 pointer for this.
        //This is the "merge" step of merge sort, with the exception that we don't actually have 2 same size.
        //Steps:
        /*
            1. Create 2 ints representing positions of indices at nums1 and nums2
            2. Compare.
                2a. If nums1[i] <= nums2[j], we can simply move forward i by 1.
                2b. If nums1[i] > nums2[j], then we gotta do some funky stuff.
                    The *main* problem is that we can't just set nums1[i] = nums2[j], because we'd lose nums1[i] (the original).
                    -> Of course, we could move all the elements down, but then that would be STUPID slow.
                    Big question: How do we do this quickly?
                    Solution 1: Use a temporary array and reset nums1 after?
                        We'd create a numsAfter, which would be size M + N, and just push into there.
                        This would operate similar to mergesort.
                        We'd end up with O(m+n) + O(m+n) = O(2(m+n)) due to the second push.
                        
                    That's...linear? Can we actually do faster?
        */
        int[] numResult = new int[m+n];
        int numResultPointer = 0;
        int nums1Pointer = 0;
        int nums2Pointer = 0;
        while(nums1Pointer < m && nums2Pointer < n) { //We do this because nums1 is size m + n.
            if(nums1[nums1Pointer] <= nums2[nums2Pointer]) {
                numResult[numResultPointer] = nums1[nums1Pointer];
                nums1Pointer++;
            } else {
                numResult[numResultPointer] = nums2[nums2Pointer];
                nums2Pointer++;
            }
            numResultPointer++;
        }
        //Now, either nums1Pointer or nums2Pointer is OOB. We need to check which one
        if(nums1Pointer >= m) {
            while(nums2Pointer < n) {
                numResult[numResultPointer] = nums2[nums2Pointer];
                nums2Pointer++;
                numResultPointer++;
            }
        } else if (nums2Pointer >= n) {
            while(nums1Pointer < m) {
                numResult[numResultPointer] = nums1[nums1Pointer];
                nums1Pointer++;
                numResultPointer++;
            }
        }
        
        //Now, export the whole thing to nums1
        for(int i = 0; i < m+n; i++) {
            nums1[i] = numResult[i];
        }
    }
}