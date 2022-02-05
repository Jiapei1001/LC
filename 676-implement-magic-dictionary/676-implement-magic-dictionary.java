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
    
    // Recursion
    public boolean search(String searchWord) {
        return dfs(searchWord, 0, this.root, false);
    }
    
    private boolean dfs(String s, int i, TrieNode curr, boolean flag) {
        if (i == s.length() && curr.isWord && flag) {
            return true;
        }
        if (i >= s.length()) {
            return false;
        }
        
        char c = s.charAt(i);
        
        // 此处是两种平行的情况
        // 不改变，继续找
        // don't use flag
        if (curr.children.containsKey(c)) {
            if (dfs(s, i + 1, curr.children.get(c), flag)) {
                return true;
            }
        }
        
        // 改变，那么下一个char next必须不是char c
        // must not used before
        if (!flag) {
            for (char next : curr.children.keySet()) {
                // NOTE: here next MUST NOT EQUAL TO c
                if (next != c && dfs(s, i + 1, curr.children.get(next), true)) {
                    return true;
                }
            }
        }
        
        return false;
    }
    
    
    // Iteration
    /*
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
    */
    
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