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
        //Maybe do an in-order traversal, modifying the max depth?
        //We should do this recursively!
        if(root == null) return 0;
        int maxDepth = 1;
        if(root.left != null) {
            int leftMaxDepth = maxDepth(root.left) + 1; //If we have a left node, we go there. We add 1 since any child of such a node has a depth of its depth + 1.
            if(leftMaxDepth > maxDepth) {
                maxDepth = leftMaxDepth;
            }
            
        }
        if(root.right != null) {
            int rightMaxDepth = maxDepth(root.right) + 1;
            if(rightMaxDepth > maxDepth) {
                maxDepth = rightMaxDepth;
            }
        }
        return maxDepth;
    }
}