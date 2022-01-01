/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */

class Solution {
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        Stack<TreeNode> pAncestors = new Stack<>();
        Stack<TreeNode> qAncestors = new Stack<>();
        
        int pAncestorSize = 0;
        int qAncestorSize = 0;
        
        Stack<TreeNode> DFS = new Stack<>();
        DFS.push(root);
    
        //Build pAncestorsTree
        while(!DFS.isEmpty()) {
            TreeNode node = DFS.pop();
            pAncestors.push(node);
            pAncestorSize++;
            if(node.val != p.val) {
                if(node.val < p.val) {
                    DFS.push(node.right);
                } else {
                    DFS.push(node.left);
                }
            }
        }
        
        DFS.push(root);
        //Build qAncestorsTree
        while(!DFS.isEmpty()) {
            TreeNode node = DFS.pop();
            qAncestors.push(node);
            qAncestorSize++;
            if(node.val != q.val) {
                if(node.val < q.val) {
                    DFS.push(node.right);
                } else {
                    DFS.push(node.left);
                }
            }
        }
        
        //Compare the two stacks
        while(!pAncestors.isEmpty() && !qAncestors.isEmpty()) {
            if(qAncestorSize < pAncestorSize) {
            if(qAncestors.peek() == pAncestors.pop()) {
                return qAncestors.peek();
                } else {
                    pAncestorSize--;
            }
        } else if (qAncestorSize > pAncestorSize) {
                if(qAncestors.pop() == pAncestors.peek()) {
                    return pAncestors.peek();
                } else {
                    qAncestorSize--;
                }
        } else {
            TreeNode common = qAncestors.pop();
            if(common == pAncestors.pop()) {
                return common;
            } else {
                pAncestorSize--;
                qAncestorSize--;
            }
        }
        }
        return root;
    }
}