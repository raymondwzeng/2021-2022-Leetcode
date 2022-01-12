/*
Failed attempt. Got *close* to where we needed to be, but couldn't figure out how to avoid duplicates.
        Arrays.sort(candidates); //Sort candidates, this will be useful later on.
        List<List<Integer>> result = new LinkedList<List<Integer>>();
        HashMap<Integer, List<List<Integer>>> memo = new HashMap<>(); //This will serve as our "combos" for numbers under target.
        for(int i = 1; i <= target; i++) { //Make a combo list of all numbers under target. Start at 1, because we don't have 0.
            for(int j = 0; j < candidates.length; j++) {
                if(i % candidates[j] == 0 && i != target) {
                    List<List<Integer>> comboList;
                    List<Integer> newList = new LinkedList<Integer>();
                    if(memo.get(i) == null) {
                        comboList = new LinkedList<List<Integer>>();
                    } else {
                        comboList = memo.get(i);
                    }
                    for(int k = 1; k <= i/candidates[j]; k++) {
                        newList.add(candidates[j]); //Add k candidates into the list
                    }
                    comboList.add(newList);
                    if(memo.get(i) == null) memo.put(i, comboList); //Add combolist to memo if not exist.
                }
            }
            if(memo.get(i) != null && memo.get(target-i) != null) { //If we're in the positive end of the list and the other list exists...
                System.out.println("Combining " + i + " and " + (target-i));
                //Join all possible combinations of the two lists. How do we do that again?
                if(false) {
                    //Weird case where the number is exactly half of the target, we don't want it to cartesian product itself.
                    List<Integer> possibleResult = new LinkedList<Integer>();
                    possibleResult.addAll(memo.get(i).get(0));
                } else {
                  for(int list1 = 0; list1 < memo.get(i).size(); list1++) {
                      System.out.println("Outer: " + memo.get(i).get(list1));
                        for(int list2 = 0; list2 < memo.get(target-i).size(); list2++) {
                            System.out.println("Inner: " + memo.get(target-i).get(list2));
                            //Double-nested for loop to get the combo list of all the of the lists.
                            List<Integer> possibleResult = new LinkedList<Integer>();
                            possibleResult.addAll(memo.get(i).get(list1));
                            possibleResult.addAll(memo.get(target-i).get(list2));
                            result.add(possibleResult);
                        }
                    }  
                }   
            } else if(memo.get(i) != null && i == target) { //Alternative case: We have the actual target in our thingy
                System.out.println("Adding final, where i is " + i + " and the list is " + memo.get(i).get(0));
                result.add(memo.get(i).get(0)); //There should only be 1 item in our list anyways.
            }
        }
        
        return result;
*/

class Solution {
    void solve(int allowedIndex, LinkedList<Integer> runningList, int runningTarget, int[] candidates, int target, List<List<Integer>> result) {
            if(runningTarget == target) {
                List<Integer> clone = new LinkedList<Integer>(runningList);
                result.add(clone);
                return;
            }
            if(runningTarget > target || allowedIndex >= candidates.length) {
                return; //Kill this current search if we've gone over the target, or the allowed index is over the length of candidates.
            }
        //Decision tree
            // -> 1 route is add a copy of our current index.
            // -> Another route is move on to next index.
            // 2 branches per layer, depth is up to our target, thus runtime is O(2^t)
            runningList.add(candidates[allowedIndex]);
            solve(allowedIndex, runningList, runningTarget+candidates[allowedIndex], candidates, target, result);
            runningList.removeLast();
            solve(allowedIndex+1, runningList, runningTarget, candidates, target, result);
    }
    
    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        //"Classic solution" using backtracking (courtesy of NeetCode as always)
        List<List<Integer>> result = new LinkedList<List<Integer>>();
        solve(0, new LinkedList<Integer>(), 0, candidates, target, result);
        
        return result;
    }
}