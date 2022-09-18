/**
 * Definition for a binary tree node.
 * class TreeNode(var `val`: Int = 0) {
 *     var left: TreeNode? = null
 *     var right: TreeNode? = null
 * }
 */
/* This approach didn't really work due to the fact that there wasn't really a way for the algorithm when looking at the nodes from the root, which side was closer to the target and which was away.
    fun depth(root: TreeNode?, target: TreeNode?): Int {
        if(root == null) return -1
        if(root == target) return 1
        val leftDepth = depth(root.left, target)
        if(leftDepth != -1) return leftDepth + 1
        val rightDepth = depth(root.right, target)
        if(rightDepth != -1) return rightDepth + 1
        return -1
    }
    
    fun getChildrenKFromRoot(root: TreeNode?, target: TreeNode?, k: Int): List<Int> {
        if(root == null) return emptyList<Int>()
        val currList = if(k == 0 && root != target) listOf<Int>(root.`val`) else emptyList<Int>()
        val leftList = if(root.left != null && k > 0) getChildrenKFromRoot(root.left, target, k - 1) else emptyList<Int>()
        val rightList = if(root.right != null && k > 0) getChildrenKFromRoot(root.right, target, k - 1) else emptyList<Int>()
        return currList + leftList + rightList
    }
    
    fun distanceK(root: TreeNode?, target: TreeNode?, k: Int): List<Int> {
        /*
            If we do an inorder traversal, then there are a few spots to take a look at:
                - Each child (left and right) and their children and so on, until we reach either over the end or a node.
                - All children k - d from the root (where d is the depth/distance from the root) - this is because we need to account for that distance.
                
            3 Passes
            The first pass will be used to actually get the depth of the node.
            The second pass then will be used to get all nodes at least that k - depth away from that node.
            The third pass then will be used on the node itself, finding the children that are k away from it.
        */
        println(getChildrenKFromRoot(target, target, k))
        println(getChildrenKFromRoot(root, target, Math.abs(k - (depth(root, target) - 1))))
        return getChildrenKFromRoot(target, target, k) + getChildrenKFromRoot(root, target, Math.abs(k - (depth(root, target) - 1)))
    }
*/

class Solution {
    fun populateMap(root: TreeNode, map: MutableMap<TreeNode, TreeNode?>) {
        if(root.left != null) {
            map.put(root.left, root)
            populateMap(root.left, map)
        }
        if(root.right != null) {
            map.put(root.right, root)
            populateMap(root.right, map)
        }
    }
    
    fun distanceK(root: TreeNode?, target: TreeNode?, k: Int): List<Int> {
        /*
            An approach is to make the digraph a regular graph, by creating an adjacency map of nodes and their parent.
            
            Then run a BFS from the target, keeping track of "seen" nodes so we don't traverse them again.
        */
        if(root == null || target == null) return emptyList<Int>()
        val parentMap = mutableMapOf<TreeNode, TreeNode?>(root to null)
        populateMap(root, parentMap)
        
        //Run BFS with a seen set.
        val seenSet = mutableSetOf<TreeNode>()
        val BFSQueue = LinkedList<TreeNode>()
        BFSQueue.add(target)
        for(i in 0..k - 1) { //Find all nodes k away, so go until k.
            for(nodeIndex in 0..BFSQueue.size - 1) { //Explore all neighbors of this node
                val front = BFSQueue.poll()
                if(parentMap.get(front) != null && !seenSet.contains(parentMap.get(front)!!)) BFSQueue.add(parentMap.get(front)!!)
                if(front.left != null && !seenSet.contains(front.left)) BFSQueue.add(front.left)
                if(front.right != null && !seenSet.contains(front.right)) BFSQueue.add(front.right)
                seenSet.add(front)
            }
        }
        return BFSQueue.map{node -> node.`val`}
    }
}