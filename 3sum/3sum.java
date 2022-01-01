/*
Failed attempt #1
        public int binarySearch(int[] nums, int left, int right, int target) {
        int halfway = (right+left)/2;
        if(left > right || right > nums.length - 1 || left < 0) return -1; //Return a non-index if left < right.
        if(nums[halfway] == target) { //If same, return the index.
            return halfway;
        } else if (nums[halfway] < target) { //If less, check right side.
            return binarySearch(nums, halfway + 1, right, target);
        } else {
            return binarySearch(nums, left, halfway - 1, target);
        }
    }

        int left = 0;
        int right = nums.length - 1;
        /*
        Loop Invariate:
            As the subarray between left and right is sorted, then the remaining value to make left + right + middle = 0 should be in between.
            If this is imbalanced, we only move it such that the remaining value should have been picked up before.
        
        //Error 1: Sort of imbalanced array.
        //We end up "missing" a potential value. We can try to solve this by using a HashMap?
        HashMap<Integer, Integer> previousValues = new HashMap<Integer, Integer>();
        while(left < right) {
            int remainder = (nums[left] + nums[right]);
            int needNumber = -remainder;
            int potentialIndex = binarySearch(nums, left + 1, right - 1, needNumber);
            if(potentialIndex != -1 || previousValues.get(needNumber) != null) { //I'm not sure if we need the last 2 checks given our search radius.
                if(previousValues.get(needNumber) != null) potentialIndex = previousValues.get(needNumber); //Edge case accounted for?
                //We found a value in between
                ArrayList<Integer> combo = new ArrayList<Integer>();
                combo.add(nums[left]);
                combo.add(nums[right]);
                combo.add(nums[potentialIndex]);
                result.add(combo);
            }
            
            if(previousValues.get(nums[left]) != null) {
                previousValues.put(nums[left], left);
            }
            if(previousValues.get(nums[right]) != null) {
                previousValues.put(nums[right], right);
            }
            if(remainder < 0) {
                //We're too deep into the negatives, shift left right.
                left++;
            } else if (remainder > 0) {
                //Too far in the positives, shift right left
                right--;
            } else { //Converge them!
                left++;
                right--;
            }
        }
*/

class Solution {
    public List<List<Integer>> threeSum(int[] nums) {
        //So i != j, k && j != k, so they can ONLY be used once.
        //Furthermore, i + j + k = 0.
        //Potential solution: Start by sorting, then use 2 pointers on the left & right
        List<List<Integer>> result = new LinkedList<List<Integer>>();
        Arrays.sort(nums);
        // HashMap<Integer, Boolean> processedValues = new HashMap<Integer, Boolean>();
    //Outer loop invariant:
    //Since we are moving left to right, and the array is sorted in non-decreasing order,
    //we will never have a situation where we need a number less than nums[i] (because left to right).
    //That way, our numbers only worry about getting the "opposite".
        for(int i = 0; i < nums.length - 2; i++) { //Iterate through all numbers in the array, one at a time.
                if(i == 0 || (i > 0 && nums[i] != nums[i-1])) {
                    int left = i+1;
                    int right = nums.length - 1;
                    //Inner loop invariant:
                    //Since we have a sorted array, by moving the pointers towards/away the center, we move our values in that direction and
                    //won't miss any.
                    //The more important part is the outer loop imo, which is what I missed from my first solution.
                    //We iterate through all of the array elements outside, so that's O(n) already.
                    //In the worst-case scenario, we could have all of the items in a way such that each search operation takes it to the limit.
                    //Then this algorithm inside appears to be running in (n-1) + (n-2) + (n-3) + ... + (n-(n-1)) + 0 = n(n-1)/2, or O(n^2).
                    //Thus our total algorithm takes O(n^3) time.
                    //Error #2: Duplicate answers. We should be able to address this by using a hashmap of only nums[i], as if we have done it once
                    //We end up using smart looping and checks instead.
                    int neededNumber = -nums[i];
                    while(left < right) {
                        if(nums[left] + nums[right] == neededNumber) {
                            //Add & modify
                            result.add(Arrays.asList(nums[left], nums[right], nums[i]));
                            while(left < right && nums[left] == nums[left+1]) {
                                left++;
                            }
                            while(left < right && nums[right] == nums[right-1]) {
                                right--;
                            }
                            left++;
                            right--;
                        } else if (nums[left] + nums[right] < neededNumber) {
                            left++;
                        } else {
                            right--;
                        }
                    }
                }
 
        }

        return result;
    }
}