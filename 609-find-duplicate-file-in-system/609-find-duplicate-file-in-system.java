class Solution {
    public List<List<String>> findDuplicate(String[] paths) {
        List<List<String>> res = new ArrayList<>();
        
        if (paths == null || paths.length == 0) return res;
        
        
        // content -> path
        Map<String, List<String>> content2Path = new HashMap<>();
        
        for (String p : paths) {
            String[] s = p.split("\\s");
            
            // path
            String r = s[0];
            
            for (int i = 1; i < s.length; i++) {
                String f = s[i];
                String[] file = f.split("\\(");
                String fileName = file[0];
                String content = file[1].substring(0, file[1].length() - 1);
                
                String tempPath = r + '/' + fileName;
                content2Path.computeIfAbsent(content, a -> new ArrayList<String>()).add(tempPath);
            }
        }
        
        for (Map.Entry<String, List<String>> e : content2Path.entrySet()) {
            if (e.getValue().size() > 1) {
                res.add(new ArrayList<String>(e.getValue()));
            }
        }
        
        return res;
    }
}