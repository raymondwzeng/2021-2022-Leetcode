class Solution {
    //Solution from LeetCode Discussion.
    //No Dynamic Programming required (something something hammer and nails, huh?)
    //Abuse of hash table.
    public int longestConsecutive(int[] nums) {
        int longestChain = 0;
        Set<Integer> numSet = new HashSet<Integer>();
        for(int i = 0 ; i < nums.length ; i++) {  //Convert to set. Question: How much time would this take? Naively, O(n)?
            numSet.add(nums[i]);
        }
        for(int currInt : numSet) { //Iterate through all the numbers in the set.
            if(!numSet.contains(currInt-1)) { //As long as the previous does not exist (do not want to double-count)
                int checkInt = currInt; //Set a new check integer
                while(numSet.contains(checkInt + 1)) { //Is the next available?
                    checkInt++; //If so, visit.
                }
                if(checkInt - currInt + 1 > longestChain) longestChain = checkInt - currInt + 1; //Was our chain long enough? Update if yes.
                //Also, a + 1 since no matter what, our longest chain is *at least* 1 long.
            }
        }
        return longestChain; //This algorithm runs in O(n) because O(n) + O(n) = O(n). Gotta keep reminding myself of that.
        //Also, hashtables are incredibly useful. Abuse them!
    }
}