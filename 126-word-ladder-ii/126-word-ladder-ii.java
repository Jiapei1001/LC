class Solution {
    public List<List<String>> findLadders(String beginWord, String endWord, List<String> wordList) {
        Set<String> words = new HashSet<>(wordList);
        
        List<List<String>> res = new ArrayList<>();
        
        
        Map<String, List<String>> graph = new HashMap<>();
        // NOTE: 一定要使用distance来记录curr与next是否相邻，是否相差 1 
        // 也可以用来当做BFS的visited使用！！
        Map<String, Integer> distance = new HashMap<>();
        bfs(beginWord, endWord, words, graph, distance);
        
        
        List<String> path = new ArrayList<>();
        path.add(beginWord);
        dfs(beginWord, endWord, graph, distance, path, res);
        
        return res;
    }
    
    private void dfs(String curr, String target, Map<String, List<String>> graph,
                     Map<String, Integer> distance,
                     List<String> path, List<List<String>> res) {
        
        if (curr.equals(target)) {
            res.add(new ArrayList<String>(path));
            return;
        }
        
        // path.add(curr);
        if (graph.containsKey(curr)) {
            for (String next : graph.get(curr)) {
                
                if (distance.get(next) == distance.get(curr) + 1) {
                    path.add(next);
                    dfs(next, target, graph, distance, path, res);
                    path.remove(path.size() - 1);
                }
            }
        }
    }
    
    private void bfs(String b, 
                     String e, 
                     Set<String> words, 
                     Map<String, List<String>> graph,
                     Map<String, Integer> distance) {
        Queue<String> q = new LinkedList<>();
        Set<String> visited = new HashSet<>();
        
        q.offer(b);
        distance.put(b, 0);
        
        for (String s : words) graph.put(s, new ArrayList<String>());
        
        
        while (!q.isEmpty()) {
            String curr = q.poll();
            
            graph.put(curr, new ArrayList<String>());
            
            for (String cand : getOptions(curr, words)) {
                // NOTE: 这里出错了，到目前为止cand都是可行的！！
                // 因此绝对不可以把这一行加在下面check visited的内部！！
                graph.get(curr).add(cand);
                
                if (!distance.containsKey(cand)) {
                    q.offer(cand);
                    distance.put(cand, distance.get(curr) + 1);
                }
            }
        }
    }
    
    private List<String> getOptions(String curr, Set<String> words) {
        List<String> res = new ArrayList<>();
        
        int n = curr.length();
        for (int i = 0; i < n; i++) {
            char c = curr.charAt(i);

            for (char d = 'a'; d <= 'z'; d++) {
                if (d == c) continue;

                String cand = curr.substring(0, i) + d + curr.substring(i + 1);

                if (words.contains(cand)) {
                    res.add(cand);
                }
            }
        }
        
        return res;
    }
}