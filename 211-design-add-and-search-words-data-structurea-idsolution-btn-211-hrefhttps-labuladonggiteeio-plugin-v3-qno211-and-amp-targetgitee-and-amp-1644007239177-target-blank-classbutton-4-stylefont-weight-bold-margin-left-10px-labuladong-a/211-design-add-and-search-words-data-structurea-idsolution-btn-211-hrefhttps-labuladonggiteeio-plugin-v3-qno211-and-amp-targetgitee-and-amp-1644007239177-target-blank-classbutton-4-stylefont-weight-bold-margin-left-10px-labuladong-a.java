class WordDictionary {
    
    TrieNode root;

    public WordDictionary() {
        this.root = new TrieNode();
    }
    
    public void addWord(String word) {
        TrieNode curr = this.root;
        
        for (char c : word.toCharArray()) {
            curr.children.putIfAbsent(c, new TrieNode());
            curr = curr.children.get(c);
        }
        
        curr.isWord = true;
    }
    
    public boolean search(String word) {
        return searchHelper(word, this.root);
    }
    
    private boolean searchHelper(String word, TrieNode curr) {        
        for (int i = 0; i < word.length(); i++) {
            char c = word.charAt(i);
            // NOTE: 这里必须加外面一层check是否包含c
            if (!curr.children.containsKey(c)) {
                // need to check all of the possible children
                if (c == '.') {
                    String next = word.substring(i + 1);
                    for (char cand : curr.children.keySet()) {
                        if (searchHelper(next, curr.children.get(cand))) {
                            return true;
                        }
                    }
                }
                return false;
            } else {
                curr = curr.children.get(c);
            }
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
 * Your WordDictionary object will be instantiated and called as such:
 * WordDictionary obj = new WordDictionary();
 * obj.addWord(word);
 * boolean param_2 = obj.search(word);
 */