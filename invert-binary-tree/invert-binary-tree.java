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
        //Basically, we want to go layer by layer, and swap the values.
        //Maybe run BFS then, and swap the front and back of queue values?
        if (root == null) return null; //Two edge cases - single node and no node.
        if (root.left == null && root.right == null) return root;
        
        ArrayDeque<TreeNode> BFSQueue = new ArrayDeque<TreeNode>();
        BFSQueue.addFirst(root); //Add root, then we explore.
        
        while(!BFSQueue.isEmpty())
        {
            TreeNode node = BFSQueue.poll(); //Remove the top
            if(node.left != null && node.right != null) {
                //If both children exist, swap them and then add to queue.
                TreeNode tempNode = node.left;
                node.left = node.right;
                node.right = tempNode;
                BFSQueue.addLast(node.left);
                BFSQueue.addLast(node.right);
            } else if (node.left != null && node.right == null) {
                node.right = node.left;
                node.left = null;
                BFSQueue.addLast(node.right);
            } else if (node.left == null && node.right != null) {
                node.left = node.right;
                node.right = null;
                BFSQueue.addLast(node.left);
            }
        }
        //We don't need to add the root to the SwapQueue because it will never be changed.
        //New problem: It isn't as simple as just reversing all of the values. And it looks like val may be private?
        //New possible solution: Use math? I believe left is always equal to 2i+1, right 2i+2, where i is the index.
        //Then how is the swapped relative to them?: 2(i+1)+1, 2(i+2)+2?
        
        //Pause for a second. Each node retains their children, they're just switched around. Could we just run BFS with some changes?
        //This seems to work LMAO
        return root;
    }
}