class MapSum {
    
    TrieNode root;

    public MapSum() {
        this.root = new TrieNode();
    }
    
    public void insert(String key, int val) {
        TrieNode curr = root;
        
        for (char c : key.toCharArray()) {
            if (curr.children[c - 'a'] == null) {
                curr.children[c - 'a'] = new TrieNode();
            }
            // first get children
            curr = curr.children[c - 'a'];
        }
        curr.isWord = true;
        curr.sum = val;
    }
    
    public int sum(String prefix) {
        // loop to get the end of prefix
        TrieNode curr = this.root;
        for (char c : prefix.toCharArray()) {
            if (curr.children[c - 'a'] == null) {
                return 0;
            }
            // first get children
            curr = curr.children[c - 'a'];
        }
        
        int res = dfsNode(curr);
        
        return res;
    }
    
    private int dfsNode(TrieNode root) {
        if (root == null) {
            return 0;
        }
        
        int res = root.isWord ? root.sum : 0;
        
        for (char c = 'a'; c <= 'z'; c++) {
            res += dfsNode(root.children[c - 'a']);
        }
        
        return res;
    }
}

class TrieNode {
    TrieNode[] children;
    boolean isWord;
    int sum;
    
    public TrieNode() {
        this.children = new TrieNode[26];
        this.isWord = false;
        this.sum = 0;
    }
}


/**
 * Your MapSum object will be instantiated and called as such:
 * MapSum obj = new MapSum();
 * obj.insert(key,val);
 * int param_2 = obj.sum(prefix);
 */