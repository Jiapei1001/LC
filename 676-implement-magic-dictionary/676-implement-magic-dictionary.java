class MagicDictionary {
    
    TrieNode root;

    public MagicDictionary() {
        this.root = new TrieNode();
    }
    
    public void buildDict(String[] dictionary) {
        for (String s : dictionary) {
            this.insert(s);
        }
    }
    
    private void insert(String s) {
        TrieNode curr = this.root;
        
        for (char c : s.toCharArray()) {
            curr.children.putIfAbsent(c, new TrieNode());
            curr = curr.children.get(c);
        }
        
        curr.isWord = true;
    }
    
    public boolean search(String searchWord) {
        for (int i = 0; i < searchWord.length(); i++) {
            char c = searchWord.charAt(i);
            
            for (char n = 'a'; n <= 'z'; n++) {
                if (n == c) continue;
                String next = searchWord.substring(0, i) + n + 
                              searchWord.substring(i + 1);
                if (this.hasWord(next)) {
                    return true;
                }
            }
        }
        
        return false;
    }
    
    private boolean hasWord(String s) {
        TrieNode curr = this.root;
        
        for (char c : s.toCharArray()) {
            if (!curr.children.containsKey(c)) {
                return false;
            }
            curr = curr.children.get(c);
        }
        
        return curr.isWord;
    }
    
}

class TrieNode {
    Map<Character, TrieNode> children;
    boolean isWord;
    
    public TrieNode() {
        this.children = new HashMap<>();
        this.isWord = false;
    }
}

/**
 * Your MagicDictionary object will be instantiated and called as such:
 * MagicDictionary obj = new MagicDictionary();
 * obj.buildDict(dictionary);
 * boolean param_2 = obj.search(searchWord);
 */