class Solution {
    
    Set<Integer> res;
    
    public int[] smallestSufficientTeam(String[] req_skills, List<List<String>> people) {
        int n = people.size();
        
        // build graph
        Map<String, Set<Integer>> map2Ppl = new HashMap<>();
        for (int i = 0; i < people.size(); i++) {
            for (String skill : people.get(i)) {
                map2Ppl.computeIfAbsent(skill, a -> new HashSet<Integer>()).add(i);
            }
        }
        
        // initiate res as the max number first
        res = new HashSet<>();
        
        Set<Integer> path = new HashSet<>();
        
        int[] minNum = new int[1];
        minNum[0] = n;
        
        dfs(req_skills, 0, map2Ppl, path, minNum);
        
        int[] result = new int[res.size()];
        int idx = 0;
        for (int i : res) result[idx++] = i;
        
        return result;
    }
    
    private void dfs(String[] req_skills, int i, Map<String, Set<Integer>> map2Ppl, 
                     Set<Integer> path, int[] minNum) {
        if (path.size() > minNum[0]) {
            return;
        }
        
        if (i == req_skills.length) {
            if (minNum[0] > path.size()) {
                res = new HashSet<Integer>();
                res.addAll(path);
                minNum[0] = path.size();
            }
            return;
        }
        
        
        String currSkill = req_skills[i];
        Set<Integer> pplHaveSkill = map2Ppl.get(currSkill);
        
        
        // check path & pplHaveSkill intersection
        Set<Integer> intersection = new HashSet<>();
        for (int k : path) intersection.add(k);
        intersection.retainAll(pplHaveSkill);
        
        if (intersection.size() > 0) {
            dfs(req_skills, i + 1, map2Ppl, path, minNum);
            return;
        }
        
        for (int p : pplHaveSkill) {
            path.add(p);
            dfs(req_skills, i + 1, map2Ppl, path, minNum);
            path.remove(p);
        }
        
//         for (int p : pplHaveSkill) {
//             boolean isRemove = !path.contains(p);
//             path.add(p);
//             dfs(req_skills, i + 1, map2Ppl, path);
            
//             if (isRemove) path.remove(p);
//         }
        
        return;
    }
}