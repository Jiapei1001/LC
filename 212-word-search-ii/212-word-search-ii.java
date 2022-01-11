class TrieNode {
    public TrieNode[] children;
    public String foundWord;
    
    public TrieNode() {
        this.children = new TrieNode[26];
        this.foundWord = null;
    }
}


class Trie{
    TrieNode root;
    
    public Trie() {
        root = new TrieNode();
    }
    
    public void addWord(String s) {
        TrieNode curr = root;
        
        for (char c : s.toCharArray()) {
            if (curr.children[c - 'a'] == null) {
                curr.children[c - 'a'] = new TrieNode();
            }
            curr = curr.children[c - 'a'];
        }
        curr.foundWord = s;
    }
}

class Solution {
    
    private static final int[][] dirs = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};
    
    public List<String> findWords(char[][] board, String[] words) {
        Trie trie = new Trie();
        for (String s : words) trie.addWord(s);
        
        int n = board.length, m = board[0].length;
        
        Set<String> res = new HashSet<>();
        for (int r = 0; r < n; r++)
            for (int c = 0; c < m; c++) {
                dfs(board, r, c, "", trie.root, res);
            }
        
        return new ArrayList<String>(res);
    }
    
    private void dfs(char[][] board, int r, int c, String s, TrieNode curr, Set<String> res) {
        char t = board[r][c];
        
        
        if (curr.children[t - 'a'] == null) {
            return;
        }
        
        if (curr.children[t - 'a'].foundWord != null) {
            res.add(s + t);
            // NOTE: mark the foundWord as null to avoid duplicated entries
            // The DFS needs to be continued
            curr.children[t - 'a'].foundWord = null;
        }
        
        board[r][c] = '#';
        
        for (int[] dir : dirs) {
            int nr = r + dir[0];
            int nc = c + dir[1];
            
            if (nr < 0 || nr >= board.length || nc < 0 || nc >= board[0].length) {
                continue;
            }
            
            if (board[nr][nc] == '#') {
                continue;
            }
            
            dfs(board, nr, nc, s + t, curr.children[t - 'a'], res);
        }
        
        board[r][c] = t;
    }
    
    
    
    
    
    
    /*
    protected TrieNode trie;
    protected List<String> res = new ArrayList<>();
    protected char[][] _board;
    
    private void addWordsToTrie(String[] words, TrieNode trie) {
        for (String word : words) {
            TrieNode curr = trie;
            for (int i = 0; i < word.length(); i++) {
                Character ch = word.charAt(i);
                if (curr.children[ch - 'a'] == null) {
                    curr.children[ch - 'a'] = new TrieNode();
                }
                curr = curr.children[ch - 'a'];
            }
            curr.foundWord = word;
        }
    }
    
    private void backtracing(int row, int col, TrieNode root) {
        Character ch = _board[row][col];
        TrieNode curr = root.children[ch - 'a'];
        
        if (curr.foundWord != null) {
            this.res.add(curr.foundWord);
            curr.foundWord = null;
        }
        
        _board[row][col] = '#';
        int[] rowOffset = {-1, 0, 1, 0};
        int[] colOffset = {0, 1, 0, -1};
        
        for (int i = 0; i< 4; i++) {
            int newRow = row + rowOffset[i];
            int newCol = col + colOffset[i];
            
            if (newRow < 0 || newRow >= _board.length || newCol < 0 || newCol >= _board[0].length) {
                continue;
            }
            Character temp = _board[newRow][newCol];
            if (temp == '#') continue;
            if (curr.children[temp - 'a'] != null) {
                this.backtracing(newRow, newCol, curr);
            }
        }
        _board[row][col] = ch;
        
        if (curr.children == null) {
            root.children[ch - 'a'] = null;
        }
    }
    
    public List<String> findWords(char[][] board, String[] words) {
        trie = new TrieNode();
        this.addWordsToTrie(words, trie);
        this._board = board;
        
        // be careful here!!!
        // col < board[row].length NOT col < board.length!!!!!!!!!!!!!!!!!!!!!!!!!
        // easy mistake
        
        for (int row = 0; row < board.length; row++) {
            for (int col = 0; col < board[row].length; col++) {
                Character ch = board[row][col];
                if (trie.children[ch - 'a'] != null) {
                    this.backtracing(row, col, trie);
                }
            }
        }
        
        return this.res;
    }
    */
}
