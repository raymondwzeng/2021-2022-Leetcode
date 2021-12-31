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
    public boolean isSameTree(TreeNode p, TreeNode q) {
        //General idea: You can apply the comparisons between nodes on an individual level, and since they HAVE to be the same tree, it's just value comparison.
        if(p == null) {
            if(q == null) return true; //If both are null, we're ok.
            return false; //q is not null.
        } else if (q == null) {
            return false; //p is not null.
        }
        
        if(p.val != q.val) return false; //The "base case check"
        return (isSameTree(p.left, q.left) && isSameTree(p.right, q.right)); //Since both trees have to be the same, run an AND.
    }
}