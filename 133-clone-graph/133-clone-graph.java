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

class Solution {
    public Node cloneGraph(Node node) {
        if(node == null) return null;
        HashMap<Node, Node> cloneMap = new HashMap<>(); //Key is original, value is clone
        Queue<Node> BST = new LinkedList<>();
        BST.add(node);
        while(!BST.isEmpty()) {
            Node original = BST.poll();
            if(cloneMap.get(original) == null) {
                Node clone = new Node(original.val);
                cloneMap.put(original, clone);
                for(int i = 0; i < original.neighbors.size(); i++) {
                    BST.add(original.neighbors.get(i));
                }
            }
        }
        
        for(Map.Entry<Node, Node> entry : cloneMap.entrySet()) {
            if(entry.getValue().neighbors.size() == 0) {
                for(int j = 0; j < entry.getKey().neighbors.size(); j++) {
                    Node original = entry.getKey();
                    Node clone = entry.getValue();
                    clone.neighbors.add(cloneMap.get(original.neighbors.get(j)));
                }
            }
        }
        
        return cloneMap.get(node);
    }
}