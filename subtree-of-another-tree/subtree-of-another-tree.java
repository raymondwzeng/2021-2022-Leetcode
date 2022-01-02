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
    public boolean isSameTree(TreeNode root, TreeNode otherRoot) { //This is an O(m) operation, where m is the number of nodes in the subtrees.
        if(otherRoot == null) {
            if(root == null) return true;
            return false;
        } else if(root == null) {
            return false;
        }
        if(root.val != otherRoot.val) return false;
        return(isSameTree(root.left, otherRoot.left) && isSameTree(root.right, otherRoot.right));
    }
    
    public boolean isSubtree(TreeNode root, TreeNode subRoot) { //This is an O(n) operation, where n is the number of nodes in the main trees.
        if(subRoot == null) return true;
        if(root == null) return false;
        if(isSameTree(root, subRoot) == true) return true;
        return(isSubtree(root.left, subRoot) || isSubtree(root.right, subRoot));
        //Ultimately this is an O(nm) operation.
    }
}