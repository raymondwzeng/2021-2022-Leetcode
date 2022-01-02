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
    public TreeNode findLeast(TreeNode root) {
        TreeNode leastNode = root;
        while(leastNode.left != null) {
            leastNode = leastNode.left;
        }
        return leastNode;
    } 
    
    public TreeNode findGreatest(TreeNode root) {
        TreeNode greatestNode = root;
        while(greatestNode.right != null) {
            greatestNode = greatestNode.right;
        }
        return greatestNode;
    }
    
    public boolean isValidBST(TreeNode root) {
        //This problem seems to lend itself towards recursion, where we check the left and right nodes, then the left and right subtrees
        //The main problem comes from the idea of example 2, where *technically* the left and right are BSTs, but the entire tree is not.
        //Naive solution: Check the root left, right, and left + right subtrees
        //We don't need to account for null due to the conditions given
        //So traversing the right and left subtrees for the least/greatest takes O(logN) operations each time, and we perform this
        //on each node. Thus we have an O(nlogn) runtime?
        boolean leftSubtreeValid = true;
        boolean rightSubtreeValid = true;
        if(root.left != null) {
            if(root.left.val >= root.val) return false;
            leftSubtreeValid = isValidBST(root.left);
            TreeNode greatestOfLeft = findGreatest(root.left);
            if(greatestOfLeft.val >= root.val) return false; 
        }
        if(root.right != null) {
            if(root.right.val <= root.val) return false;
            rightSubtreeValid = isValidBST(root.right);
            TreeNode leastOfRight = findLeast(root.right);
            if(leastOfRight.val <= root.val) return false;
        }
        return (leftSubtreeValid && rightSubtreeValid);
    }
}