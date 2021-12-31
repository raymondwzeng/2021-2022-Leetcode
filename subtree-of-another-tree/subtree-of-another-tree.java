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
    public boolean isSametree(TreeNode root, TreeNode subRoot) {
        if(root == null) {
            if(subRoot == null) return true;
            return false;
        } else if (subRoot == null) {
            return false;
        }//Same setup as previous problem
        
        if(root.val != subRoot.val) return false;
        return (isSametree(root.left, subRoot.left) && isSametree(root.right, subRoot.right));
    }
    
    public boolean isSubtree(TreeNode root, TreeNode subRoot) {
        if(subRoot == null) return true; //All trees contain the null tree as a subtree/subgraph.
        if(root == null) return false; //If subRoot is not null, but the main tree is, we know it's false (null tree can not contain any other tree except itself.)
        
        if (isSametree(root, subRoot)) return true; //If we're dealing with the same tree, we're done.
        return (isSubtree(root.right, subRoot) || isSubtree(root.left, subRoot)); //A tree can be a supertree if either of the subtrees (l or r) are the same tree as the target subtree.
        
        //O(n) runtime, assuming that each node fails to compare at the first steps. Then it will only iterate through the tree, comparing the whole subtree once.
        //Error while redoing once - remember that we're only dealing with the subroot, comparing the tree from there. Subroot remains static.
    }
}