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
    public Stack<TreeNode> getDFSStack(TreeNode root) { //Yoinked from "same tree", problem #100
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
    
    public boolean isSubtree(TreeNode root, TreeNode subRoot) {
        /*A naive solution:
        Edge cases: root doesn't exist, subRoot doesn't exist.
        Begin by finding the subroot. -> Edge case: It doesn't in the tree.
        Once you find the subroot, perform DFS to get a DFS stack.
        Perform DFS on the subroot as well.
        Compare the stacks.
        
        Actually, could we do DFS on the subRoot, and then once we find the object, we continue? :thinking:
        */
        //Addendum: Root and Subroot will always exist in this problem.
        //Perform DFS on the subRoot.
        
        //What if we just compare the DFS stacks?
        Stack<TreeNode> subRootStack = getDFSStack(subRoot);
        ArrayList<TreeNode> subRootArray = new ArrayList<TreeNode>(subRootStack);
        Collections.reverse(subRootArray);
        Stack<TreeNode> rootCandidates = new Stack<TreeNode>(); //ArrayList of possible nodes, in case we have duplicates.
        
        Stack<TreeNode> DFSRoot = new Stack<TreeNode>();
        DFSRoot.push(root);
        
        while(!DFSRoot.isEmpty()) {
            TreeNode node = DFSRoot.pop();
            if(node.val == subRoot.val) {
                rootCandidates.push(node);
            }
            if(node.left != null) {
                DFSRoot.push(node.left);
            }
            if(node.right != null) {
                DFSRoot.push(node.right);
            }
        }
        
        boolean foundMatch = false;
        
        if(rootCandidates.isEmpty()) return false; //If we found no places for the subRoot to begin working.
        while(!rootCandidates.isEmpty()) {
            TreeNode candidateRoot = rootCandidates.pop();
            Stack<TreeNode> candidateStack = getDFSStack(candidateRoot);
            int index = 0;
            for(index = 0; index < subRootArray.size(); index++) {
                if(candidateStack.isEmpty()) break;
                TreeNode candidateNode = candidateStack.pop();
                if(candidateNode.val != subRootArray.get(index).val) {
                    break; //If they're not the same, break out.
                }
            }
            if(index == subRootArray.size() && candidateStack.isEmpty()) return true; //If they've exhausted the array AND the stack is empty.
            //Otherwise we could have a situation where we've reached the end but the candidate stack isn't (see ex.2)
        }

        return false; //If the candidateStack still has items, then we're screwed.

        //Pop until the subroot stack is exhausted. Now, we have 3 options:
        /*
            1. MainTreeStack is also exhausted. Then it is a subtree (no more additional nodes)
            2. MainTreeStack is not exhausted, which is false.
            //No, that's an incorrect assumption. Because in ex 1 we'll have a stack with 5 after 124.
            //Maybe we...don't make a stack with the main, and instead simply navigate through it?
        */
        //No no no we can just search for the subroot first, right? But can we have duplicate values?
    }
}