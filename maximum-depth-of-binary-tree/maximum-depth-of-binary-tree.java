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
    public int maxDepth(TreeNode root) {
        //Run a DFS.
        if(root == null) return 0; //Okay, one exception.
        int depth = 1; //Always 1 node between itself, no exceptions.
        
        int leftDepth = 1;
        int rightDepth = 1;
        
        if(root.left != null) {
            leftDepth = depth + maxDepth(root.left);
        }
        
        if(root.right != null) {
            rightDepth = depth + maxDepth(root.right);
        }
        return Math.max(leftDepth, rightDepth);
    }
}