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
    public boolean isSameTree(TreeNode p, TreeNode q) { //Rewritten from #100
        if(p == null) {
            if(q == null) return true;
            return false;
        } else if (q == null) {
            return false;
        }
        if(p.val != q.val) return false;
        return (isSameTree(p.left, q.left) && isSameTree(p.right, q.right));
    }
    
    public boolean isSubtree(TreeNode root, TreeNode subRoot) {
        if(subRoot == null) return true; //Null tree is subtree of any tree
        if(root == null) return false; //Null tree is not a subtree of any tree except null tree (addressed by above)
        if(isSameTree(root, subRoot)) return true;
        return (isSubtree(root.left, subRoot) || isSubtree(root.right, subRoot));
    }
}