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
class Solution {
    public boolean isValidBST(TreeNode root) {
        //Our previous solution ran in O(nlogn) time, I believe.
        //The main driver of the longer work time was due to searching for the greatest of left, least of right, which
        //required a search in O(logn) time each time.
        //What if we found a way to incorporate that into our search?
        //So we'd need an iterative solution, or maybe a recursive one still works?
        //Start by stacking right, then stack node, then stack left.
        //When we start popping, we should get a proper order.
        
        //But this solution is a bit better than the other. Iterating through in-order is O(n), then iterating through the list is O(n),
        //but we use a space of O(n) as well. tradeoffs!
        Stack<TreeNode> inOrder = new Stack<>();
        List<TreeNode> finalList = new ArrayList<>();
        
        while(root != null || !inOrder.isEmpty()) { //Yoinked from LeetCode solutions, gotta practice this.
            while(root != null) {
                inOrder.push(root);
                root = root.left;
            }
            root = inOrder.pop();
            finalList.add(root);
            root = root.right;
        }
        
        for(int i = 1; i < finalList.size(); i++) {
            if(finalList.get(i-1).val >= finalList.get(i).val) {
                return false;
            }
        }
        
        return true;
    }
}