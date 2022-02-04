class WordFilter {

    Trie trie;
    
    public WordFilter(String[] words) {
        this.trie = new Trie();
        
        for (int i = 0; i < words.length; i++) {
            String curr = words[i];
            
            // loop through curr, get all possible suffix
            for (int j = curr.length(); j >= 0; j--) {
                String temp = curr.substring(j) + "{" + curr;
                
                // need to pass in index
                this.trie.insertWord(temp, i);
            }
        }
    }
    
    public int f(String prefix, String suffix) {
        return this.trie.startsWith(suffix + "{" + prefix);
    }
}

class Trie {
    TrieNode root;
    
    public Trie() {
        this.root = new TrieNode();
    }
    
    public void insertWord(String word, int idx) {
        TrieNode curr = this.root;
        
        for (char c : word.toCharArray()) {
            if (curr.children[c - 'a'] == null) {
                curr.children[c - 'a'] = new TrieNode();
            }
            curr = curr.children[c - 'a'];
            curr.idx = idx;
        }
    }
    
    public int startsWith(String prefix) {
        TrieNode curr = this.root;
        
        for (char c : prefix.toCharArray()) {
            if (curr.children[c - 'a'] == null) {
                return -1;
            }
            curr = curr.children[c - 'a'];
        }
        
        return curr.idx;
    }
}

class TrieNode {
    TrieNode[] children;
    int idx;
    
    public TrieNode(){
        this.children = new TrieNode[27];
        this.idx = 0;
    }
}

/**
 * Your WordFilter object will be instantiated and called as such:
 * WordFilter obj = new WordFilter(words);
 * int param_1 = obj.f(prefix,suffix);
 */