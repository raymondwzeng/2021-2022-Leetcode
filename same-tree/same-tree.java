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
    public boolean isSameTree(TreeNode p, TreeNode q) {
     //2 stack approach, with null checks and such.
        if(p == null){
            if (q == null) return true; //Edge case dealing: if p is null, check q.
            return false;
        } else if (q == null) {
            if(p != null) return false; //If q is null and p isn't, return false. We don't need to recheck true, since that should have been already accounted for in the previous statement.
        }
        
        Stack<TreeNode> pStack = new Stack<TreeNode>();
        Stack<TreeNode> qStack = new Stack<TreeNode>();
        TreeNode nullNode = new TreeNode(Integer.MAX_VALUE);
        
        pStack.push(p);
        qStack.push(q);
        while(!pStack.isEmpty() && !qStack.isEmpty()) {
            TreeNode currPNode = pStack.pop();
            TreeNode currQNode = qStack.pop();
            
            if(currPNode == null && currQNode == null) {
                break; //Break out of the loop if we're dealing with nulls here.
            } else if ((currPNode != null && currQNode == null) || (currPNode == null && currQNode != null)) {
                return false;
            }
            
            //We don't need additional null checks here because we've checked if one or both are null.
            if(currPNode.val != currQNode.val) {
                return false; //If the values aren't the same, then obviously they aren't identical trees.
            }
            
            if(currPNode.right != null || currPNode.left != null) { //If we don't have a leaf node, then we can choose to add nulls.
                if(currPNode.right == null) {
                    pStack.push(nullNode);
                } else if(currPNode.right != null) {
                    pStack.push(currPNode.right);
                }
                if(currPNode.left == null) {
                    pStack.push(nullNode);
                } else if (currPNode.left != null) {
                    pStack.push(currPNode.left);
                }
            }
            if(currQNode.right != null || currQNode.left != null) {
                if(currQNode.right == null) {
                    qStack.push(nullNode);
                } else if(currQNode.right != null) {
                    qStack.push(currQNode.right);
                }
                if(currQNode.left == null) {
                    qStack.push(nullNode);
                } else if (currPNode.left != null) {
                    qStack.push(currQNode.left);
                }
            }
            // pStack.push(currPNode.right);
            // qStack.push(currQNode.right);
            // pStack.push(currPNode.left);
            // qStack.push(currQNode.left);
            //Apparently, null is allowed. But we need to check for it then.
            //Question: What about nulls? Well, if it is null, then one stack will empty before the other, and we can compare that outside.
            //Note: This was *almost* correct. We need to figure out whether a null exists, and I don't *think* stacks handle that natively, so...yeah.
        }
        
        //If one still has items to traverse while the other doesn't, it's bad. Otherwise, we're good.
        if((pStack.isEmpty() && !qStack.isEmpty()) || (!pStack.isEmpty() && qStack.isEmpty())) {
            return false;
        } else {
            return true;
        }
        //Edge cases: Both null? Well, we'll simply push, uh...null. Whoop. Gotta take care of that.
    }
}