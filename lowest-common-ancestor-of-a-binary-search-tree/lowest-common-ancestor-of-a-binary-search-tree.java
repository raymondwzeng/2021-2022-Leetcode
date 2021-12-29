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
        //Hmm...this seems like some sort of sorting/searching algorithm? Like going layer by layer?
        //Then that begs the question - how do we do BFS on a tree?
        //Again, use a stack? While the stack is not empty, we'll add the children to the stack.
        //What is the criteria then?
        
        //Or maybe we run a BST search for p, q and then compare ancestors?
        ArrayList<TreeNode> pList = new ArrayList<TreeNode>();
        ArrayList<TreeNode> qList = new ArrayList<TreeNode>();
        
        //Start at root.
        pList.add(root);
        qList.add(root);

        
        TreeNode travelNode = root;
        
        while(travelNode.val != p.val) { //We can do this since we are given p, q in list.
            if(travelNode != root) {
                pList.add(travelNode); //Append the ancestor. (Overengineered)
            }
            if(travelNode.val < p.val) {
                travelNode = travelNode.right; //If we're less than the value, go right.
            } else {
                travelNode = travelNode.left; //If we're equal or greater, go left.
            }
        }
        
        travelNode = root;
        
        while(travelNode.val != q.val) { //We can do this since we are given p, q in list.
            if(travelNode != root) {
                qList.add(travelNode); //Append the ancestor. (Overengineered)
            }
            if(travelNode.val < q.val) {
                travelNode = travelNode.right; //If we're less than the value, go right.
            } else {
                travelNode = travelNode.left; //If we're equal or greater, go left.
            }
        }
        
        pList.add(p); //We need to do this at the end, since they're always the closest/last ancestor to themselves.
        qList.add(q); //p, q can be descendants of themselves.
        //Augh, the main problem is that our two lists may not be aligned, I think?
        //Actually, maybe they do?
        ArrayList<TreeNode> shorterList = pList;
        if(pList.size() > qList.size()) {
            shorterList = qList; //We always check the shorter list since the common ancestor will exist in that one.
        }
        
        TreeNode commonAncestor = root; //Root is common to all.
        
        for(int i = 0; i < shorterList.size(); i++) {
            if(pList.get(i).val == qList.get(i).val) {
                commonAncestor = shorterList.get(i);
            }
        }
        
        return commonAncestor;
    }
}