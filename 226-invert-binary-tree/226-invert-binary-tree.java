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
    public TreeNode invertTree(TreeNode root) {
        //This probably lends itself to a recursive solution.
        //First, swap the left and right nodes, then call it on the left and right subtrees.
        //Swapping doesn't take much time, but since we go through all of the elements, we have a runtime of O(n).
        if(root == null) return root;
        if(root.left != null) {
         if(root.right != null) {
             TreeNode temp = root.left;
             root.left = root.right;
             root.right = temp;
         } else {
             root.right = root.left;
             root.left = null;
         }
        } else if(root.right != null) {
            root.left = root.right;
            root.right = null;
        }
        
        invertTree(root.left);
        invertTree(root.right);
        return root;
    }
}