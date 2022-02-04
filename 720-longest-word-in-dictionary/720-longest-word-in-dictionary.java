class Solution {
    public String longestWord(String[] words) {
        Trie trie = new Trie();
        
        Arrays.sort(words);
        
        for (String word : words) {
            trie.insert(word);
        }
        
        return trie.longestWord;
    }
}


class Trie {
    TrieNode root;
    String longestWord;
    
    public Trie() {
        this.root = new TrieNode();
        this.longestWord = "";
    }
    
    public void insert(String s) {
        TrieNode curr = this.root;
        boolean isCand = true;
        
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            
            if (curr.children[c - 'a'] == null) {
                curr.children[c - 'a'] = new TrieNode();
            }
            // this word cannot be built one character at a time by other words
            // NOTE: the last char is always new, thus must be before last char
            if (i < s.length() - 1 && curr.children[c - 'a'].isWord == false) {
                isCand = false;
            }
            curr = curr.children[c - 'a'];
        }
        curr.isWord = true;
        
        if (isCand && (s.length() > this.longestWord.length() || s.compareTo(this.longestWord) < 0)) {
            this.longestWord = s;
        }
    }
}

class TrieNode {
    TrieNode[] children;
    boolean isWord;
    
    public TrieNode() {
        this.children = new TrieNode[26];
        this.isWord = false;
    }
}