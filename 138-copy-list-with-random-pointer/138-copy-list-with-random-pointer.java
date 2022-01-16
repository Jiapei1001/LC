/*
// Definition for a Node.
class Node {
    int val;
    Node next;
    Node random;

    public Node(int val) {
        this.val = val;
        this.next = null;
        this.random = null;
    }
}
*/

class Solution {
    
        
    Map<Node, Node> map = new HashMap<>();
    
    public Node copyRandomList(Node head) {
        if (head == null) {
            return head;
        }
        
        if (map.containsKey(head)) {
            return map.get(head);
        }
        
        map.putIfAbsent(head, new Node(head.val));
        
        map.get(head).next = copyRandomList(head.next);
        map.get(head).random = copyRandomList(head.random);
        
        return map.get(head);
    }
    
    
    
    
    
    
    /*
    // map between oldNode and newNode
    Map<Node, Node> visited = new HashMap<>();
    
    public Node getCloneNode(Node node) {
        if (node != null) {
            if (this.visited.containsKey(node)) {
                return this.visited.get(node);
            } else {
                this.visited.put(node, new Node(node.val, null, null));
                return this.visited.get(node);
            }
        }
        return null;
    }
    
    public Node copyRandomList(Node head) {
        if (head == null) return head;
        
        Node oldNode = head;
        Node newNode = new Node(oldNode.val);
        this.visited.put(oldNode, newNode);
        
        while (oldNode != null) {
            newNode.random = this.getCloneNode(oldNode.random);
            newNode.next = this.getCloneNode(oldNode.next);
            
            oldNode = oldNode.next;
            newNode = newNode.next;
        }
        
        return this.visited.get(head);
    }
    */
}