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
    //Attempt 3! This time, let's try something recursive.
    public boolean isSameTree(TreeNode p, TreeNode q) {
        if(p == null) {
            if(q == null) return true;
            return false;
        } else if (q == null) {
            return false; //We know from above check that if both null, return true.
        }
        if(p.val != q.val) return false;
        return (isSameTree(p.left, q.left) && isSameTree(p.right, q.right));
    }
}