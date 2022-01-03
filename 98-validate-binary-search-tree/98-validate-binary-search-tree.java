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
    public TreeNode findMin(TreeNode root) {
        if(root == null) return null;
        while(root.left != null) {
            root = root.left;
        }
        return root;
    }
    
    public TreeNode findMax(TreeNode root) {
        if(root == null) return null;
        while(root.right != null) {
            root = root.right;
        }
        return root;
    }
    
    public boolean isValidBST(TreeNode root) {
        //A much more concise solution. Check left and right subtrees for the min value, and then report them.
        //I don't *think* this is too efficient though, considering that it runs an O(logn) search on all nodes, so O(nlogn) runtime.
        if(root == null || (root.left == null && root.right == null)) return true;
        if((findMin(root.right) != null && findMin(root.right).val <= root.val) || (findMax(root.left) != null && findMax(root.left).val >= root.val)) return false;
        return (isValidBST(root.left) && isValidBST(root.right));
    }
}