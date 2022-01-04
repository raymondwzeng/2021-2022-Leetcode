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
        List<List<Integer>> result = new LinkedList<>();
        if(root == null) return result;
        Queue<Queue<TreeNode>> BST = new LinkedList<>();
        Queue<TreeNode> initialList = new LinkedList<>();
        initialList.add(root);
        BST.add(initialList);
        while(!BST.isEmpty()) {
            Queue<TreeNode> levelStack = BST.poll();
            List<Integer> levelList = new LinkedList<>();
            Queue<TreeNode> nextLevel = new LinkedList<>(); 
            while(!levelStack.isEmpty()) {
                TreeNode node = levelStack.poll();
                levelList.add(node.val); //Add the node to the level list, in the proper order (as they are in the stack). Hopefully this is ok?
                if(node.left != null) nextLevel.add(node.left); //Push in proper order, that way BST orders correctly and handles single-child nodes.
                if(node.right != null) nextLevel.add(node.right);
            }
            if(!levelList.isEmpty()) result.add(levelList);
            if(!nextLevel.isEmpty()) BST.add(nextLevel);
        }
        //Will this work? Well, for each level, we first check out the stack. This pops the stack with just 3 in it, then adds 20, 9 in LIFO order.
        //Then the stack is empty, list with 3 is added, and stack with 20, 9 is added.
        //9 is popped. No childre, but a new stack and list is created, with the 9 inside the list.
        //20 is popped. 2 children, 7, 15 are added to stack in LIFO order.
        //Stack is now empty, so the new layer stack is added to the list, and the list is appended to.
        //What if 9 had a child? Well, it would be added to the stack, then 15 and 7. Oof, that won't work. I guess we do really need FIFO then?
        //And we need to reverse the order of the nodes.
        return result;
    }
}