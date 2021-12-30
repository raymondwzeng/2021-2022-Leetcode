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
    public Stack<TreeNode> getDFSStack(TreeNode root) {
        TreeNode nullNode = new TreeNode(Integer.MAX_VALUE);
        Stack<TreeNode> DFS = new Stack<TreeNode>();
        Stack<TreeNode> result = new Stack<TreeNode>();
        
        DFS.push(root);
        result.push(root);
        
        while(!DFS.isEmpty()) {
            TreeNode node = DFS.pop();
            if(node.left != null) {
                DFS.push(node.left);
                result.push(node.left);
            } else if (node.left == null && node.right != null) {
                result.push(nullNode);
            }
            if(node.right != null) {
                DFS.push(node.right);
                result.push(node.right);
            } else if (node.right == null && node.left != null) { //We don't want to push nulls when both children are null (leaves)
                result.push(nullNode);
            }
        }
        
        return result;
    }
    
    public boolean isSameTree(TreeNode p, TreeNode q) {
        //Why DFS is better in this case
        //Honestly, there's not much of a difference IMO.
        //We still need some "nullNode" because otherwise structure is a bit agnostic.
        
        //WHOOPS forgot about edge cases.
        //3 in total - p null, q null, both null.
        if(p == null) {
            if(q == null) return true;
            return false;
        } else if (q == null) { //Since we already check that p is null before q is null, then if both were null then the first step would have been triggered.
            return false;
        }
        
        
        Stack<TreeNode> pDFS = getDFSStack(p);
        Stack<TreeNode> qDFS = getDFSStack(q);
        
        while(!pDFS.isEmpty()) {
            TreeNode pNode = pDFS.pop(); //No checks needed because it's our while loop condition.
            if(qDFS.isEmpty()) return false; //They are not the same - one is larger than the other.
            TreeNode qNode = qDFS.pop();
            
            if(pNode.val != qNode.val) return false; //If they are not the same in DFS order, then we can return false.
        }
        
        return (pDFS.isEmpty() && qDFS.isEmpty());
    }
}