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
    public List<List<Integer>> levelOrder(TreeNode root) {
        //BST is the proper method for this, since it goes in layers.
        //We just need to keep in mind where each layer begins.
        List<List<Integer>> result = new LinkedList<>();
        if(root != null) {
            Queue<Queue<TreeNode>> BSTOrder = new LinkedList<>();
            Queue<TreeNode> baseList = new LinkedList<>();
            baseList.add(root);
            BSTOrder.add(baseList);
            while(!BSTOrder.isEmpty()) {
                Queue<TreeNode> orderQueue = BSTOrder.poll(); //Poll 1 stack from the queue.
                if(!orderQueue.isEmpty()) {
                    Queue<TreeNode> childrenLayer = new LinkedList<>();
                    //OrderStack is not empty, that means that we have a new layer proper.
                    ArrayList<Integer> layer = new ArrayList<>();
                    while(!orderQueue.isEmpty()) {
                        //The first object 
                        TreeNode node = orderQueue.poll();
                        layer.add(node.val);
                        if(node.left != null) childrenLayer.add(node.left);
                        if(node.right != null) childrenLayer.add(node.right);
                    }
                    result.add(layer);
                    BSTOrder.add(childrenLayer);
                }
            }
        }
        return result;
    }
}