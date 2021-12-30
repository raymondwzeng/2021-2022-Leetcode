/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode() {}
 *     TreeNode(int val) { this.val = val; }
 *     TreeNode(int val, TreeNode left, TreeNode right) {
 *         this.val = val;
 *         this.left = left;
 *         this.right = right;
 *     }
 * }
 */

/**
Graveyard of failed strategies
//Layer by layer addition (failed to account for non-complete trees)

        int layer = 0;
        int addedNodes = 0;
            if(addedNodes == 0) { //We have 0 nodes in this layer so far, so we need to make a new one.
                mostRecentLayerList = new ArrayList<Integer>();
            }
            
            if(addedNodes < Math.pow(2, layer)) { //This is a separate check, so even if there was no addedNode before, we append here.
                mostRecentLayerList.add(front.val);
                addedNodes++; //Add to the list, and then add to number of nodes.
            }
            
            if(addedNodes >= Math.pow(2, layer) || BFSQueue.isEmpty()) { //If we're over the maximum afterwards (or at the max), then append and reset.
                //We add an additional case where if the Queue is empty AFTER we pop, then we've reached the end of a layer prematurely (noncomplete). We also eliminate(?) the need for that weird null check.
                returnList.add(mostRecentLayerList);
                layer++;
                addedNodes = 0;
            }

*/
class Solution {
    public List<List<Integer>> levelOrder(TreeNode root) {
        ArrayList<List<Integer>> returnList = new ArrayList<List<Integer>>();
        if(root == null) return returnList; //Just return a blank list if there's nothing.
        
        //BFS would be the best use for this. But how do we separate the items into the lists?
        //Okay, so we create a new list for each layer. So let's just use some counters then?
        //Maybe BFS was a red herring - we could use DFS to get proper layer count?
        //But how would that even work?
        //Create a new depth counter, index into the arraylist? That could work, let's brush up on iterative DFS though.
        ArrayDeque<TreeNode> BFSQueue = new ArrayDeque<TreeNode>();
        BFSQueue.add(root);

        ArrayList<Integer> mostRecentLayerList = null;
        TreeNode headOfLayer = root; //Start with head of layer as root.
        boolean searching = false;
        while(!BFSQueue.isEmpty()) {
            TreeNode front = BFSQueue.poll(); //Grab the earliest node.
            
            //Trying a new "head of layer system", where if we pop a head of layer, then we push existing list onto return list, and make new.
            if(front == headOfLayer) {
                if(mostRecentLayerList != null) {
                    returnList.add(mostRecentLayerList);
                }
                mostRecentLayerList = new ArrayList<Integer>(); //Moved outside for the sake of root. This shouldn't mess with anything anyways.
                if(front.left != null) {
                    headOfLayer = front.left;
                } else if (front.right != null) {
                    headOfLayer = front.right; //elseif case here because we always want the head of layer to be the first (left).
                } else {
                    searching = true;
                }
            }
            
            mostRecentLayerList.add(front.val); //This applies to all cases, we just need to change which list it moves to.
            
            if(front.left != null) {
                if(searching == true) { //If we're looking for a new head, then crown a new head.
                    headOfLayer = front.left;
                    searching = false;
                }
                BFSQueue.add(front.left);
            }
            
            if(front.right != null) {
                if(searching == true) {
                    headOfLayer = front.right;
                    searching = false;
                }
                BFSQueue.add(front.right);
            }
        }
        
        returnList.add(mostRecentLayerList);
        
        return returnList;
    }
}