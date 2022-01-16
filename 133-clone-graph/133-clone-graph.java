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
        if (node == null) {
            return node;
        }
        
        Map<Node, Node> map = new HashMap<>();
        
        Queue<Node> q = new LinkedList<>();
        
        q.offer(node);
        
        while (!q.isEmpty()) {
            Node curr = q.poll();
            
            Node copy = new Node(curr.val);
            
            map.putIfAbsent(curr, copy);
            
            for (Node next : curr.neighbors) {
                // enqueue
                if (!map.containsKey(next)) {
                    map.putIfAbsent(next, new Node(next.val));
                    
                    q.offer(next);
                }
                
                // mapping
                map.get(curr).neighbors.add(map.get(next));
            }
        }
        
        return map.get(node);
    }

    
    
    
    
    // DFS
    /*
    Map<Node, Node> map = new HashMap<>();
    
    public Node cloneGraph(Node node) {
        if (node == null) {
            return node;
        }
        
        if (map.containsKey(node)) {
            return map.get(node);
        }
        
        Node copy = new Node(node.val);
        map.put(node, copy);
        
        for (Node next: node.neighbors) {
            copy.neighbors.add(this.cloneGraph(next));
        }
        
        return copy;
    }
    */
    
    
    
    
    /*
    private HashMap <Node, Node> visited = new HashMap<>();
    
    public Node cloneGraph(Node node) {
        if (node == null) {
            return node;
        }
        
        // if the node has already been visited before
        if (visited.containsKey(node)) {
            return visited.get(node);
        }
        
        // create a clone
        Node cloneNode = new Node(node.val, new ArrayList());
        visited.put(node, cloneNode);
        
        // iterate through the neighbours to generate their clones
        // recursion
        for (Node neighbor : node.neighbors) {
            cloneNode.neighbors.add(this.cloneGraph(neighbor));
        }
        
        return cloneNode;
    }
    */
}
