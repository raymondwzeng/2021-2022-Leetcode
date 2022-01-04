/*
// Definition for a Node.
class Node {
    public int val;
    public List<Node> neighbors;
    public Node() {
        val = 0;
        neighbors = new ArrayList<Node>();
    }
    public Node(int _val) {
        val = _val;
        neighbors = new ArrayList<Node>();
    }
    public Node(int _val, ArrayList<Node> _neighbors) {
        val = _val;
        neighbors = _neighbors;
    }
}
*/

/*
Okay, I have a better idea now. Let's move everything back
        //So we're given a node, which contains a value and all of its neighbors.
        //Sure, we could create a node for each of their neighbors as well, but how do we avoid doing extra?
        //Well, in this case, since each node's value is the same as their index, we can maybe assume that the values are unique.
        //Thus, we can use some data structure to keep track of the numbers we've seen before. Maybe a hashmap.
        // HashMap<Integer, Boolean> visitedNodes = new HashMap<>();
        //Start by creating a copy of the first node.
        // Node newNode = new Node(node.val, )
        //It almost seems like we need to find the node (after this one) which ONLY has our start as a neighbor.
        //For each neighbor in the original node, we create a new one. But then we need to consider all of their neighbors as well!
        // Node[] nodeList = new Node[100];
        // ArrayList<Node> = new ArrayList<>();
        // for(int i = 0; i < node.neighbors.size(); i++) {
        //     node[node.neighbors.get(i).val] = new Node
        // }
        // nodeList[0] = new Node(node.val, )
            //Okay, we could run cloneGraph on the neighbors, but how can we tell which ones have been made?
        //For example, 1 connected to 2, connected to 3, 4, and 3 also connected to 4. then 1 calls 2, calls, 3, calls 4, but then 2 calls 4 again...
        //Unless we do a search?
        
        //Maybe we just run a DFS/BFS sort of things, with added DS for keeping track of existing nodes?
        Node[] nodeList = new Node[100];
        Queue<Node> BFS = new LinkedList<>();
        HashMap<Node, Boolean> visitedOnce = new HashMap<Node, Boolean>();
        BFS.add(node);
        
        while(!BFS.isEmpty()) {
            Node curr = BFS.poll();
            if(nodeList[curr.val] == null) { //If we have not seen this node before...
                Node newNode = new Node(curr.val); //Just create the new node, we'll deal with the others later.
                nodeList[curr.val] = newNode;
                for(int i = 0; i < curr.neighbors.size(); i++) {
                    BFS.add(curr.neighbors.get(i));
                }
            }
        } //This is the BFS which creates all the nodes.
        
        BFS.add(node); //Add back the node for the second time, this thime we're going to go through and get linking.
        while(!BFS.isEmpty()) {
            Node nextCurr = BFS.poll();
            //Assume nodeList[curr.val] != null
            if(visitedOnce.get(nextCurr) == null) {
                for(int i = 0; i < nextCurr.neighbors.size(); i++) {
                    nodeList[nextCurr.val].neighbors.add(nodeList[nextCurr.neighbors.get(i).val]);
                    BFS.add(nextCurr.neighbors.get(i)); //Gah, another cycle issue. How do we avoid this?
                }
                visitedOnce.put(nextCurr, true);
            }

        }
        
        for(int i = 0; i < nodeList.length; i++) {
            if(nodeList[i] != null) {
                System.out.println("For node: " + nodeList[i].val);
                for(int j = 0; j < nodeList[i].neighbors.size(); j++) {
                    System.out.println(nodeList[i].neighbors.get(j).val);
                }
            }
        }
        
        return nodeList[1];
*/

class Solution {
    public Node cloneGraph(Node node) {
        if(node == null) return null; //Edge case 1: Null node
        HashMap<Node, Node> cloneMap = new HashMap<>();
        Queue<Node> BFS = new LinkedList<>();
        BFS.add(node); //We still need BFS, but we also need the HM to check. We will use original as key, clone as value.
        
        Node returnNode = null;
        
        while(!BFS.isEmpty()) {
            Node original = BFS.poll();
            if(cloneMap.get(original) == null) {
                Node clone = new Node(original.val);
                if(original.val == 1) returnNode = clone;
                cloneMap.put(original, clone);
                for(int i = 0; i < original.neighbors.size(); i++) {
                    //Another possible optimization - maybe check if the clone exists + no neighbors?
                    BFS.add(original.neighbors.get(i)); //Add the rest to be made in the BFS order.
                }
            } else {
                if(cloneMap.get(original).neighbors.size() == 0) {
                    for(int i = 0; i < original.neighbors.size(); i++) {
                    Node cloneNeighbor = cloneMap.get(original.neighbors.get(i));
                    if(cloneNeighbor != null) { //If the other node already exists...
                        cloneMap.get(original).neighbors.add(cloneNeighbor);
                        if(cloneNeighbor.neighbors.size() == 0) { //Weird edge case where a node with degree 1 won't be linked.
                            BFS.add(original.neighbors.get(i));
                        }
                    }
                }
                }

            } //We can't do an else and connect the links, because then we'd run into the issue of potentially nulls being added.
            //Unless...we don't mind? We can try that in a bit.
        }
        
//         for(Map.Entry<Node, Node> entry : cloneMap.entrySet()) { //Iterate between every item in the entry
//             Node original = entry.getKey();
//             Node clone = entry.getValue();
            
//             for(int i = 0; i < original.neighbors.size(); i++) {
//                 clone.neighbors.add(cloneMap.get(original.neighbors.get(i))); //Link the actual nodes.
//             }
//         }
        
        //Since we are running BFS once, then going through each node again essentially, this algorithm runs in O(n^2) time I believe.
        
        return returnNode;
        
    }
}