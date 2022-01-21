class Solution {
    public List<Integer> findAnagrams(String s, String p) {
        List<Integer> res = new ArrayList<>();
        
        int n = s.length();
        int m = p.length();
        
        if (s == null || s.length() == 0 || n < m) {
            return res;
        }
        
        char[] sArr = s.toCharArray();
        int[] target = getAnagram(p);
        
        StringBuilder sb = new StringBuilder();
        int i = 0, j = 0;
        while (j <= n) {
            while (j< m) {
                sb.append(sArr[j]);
                j++;
            }
            int[] temp = getAnagram(sb.toString());
            if (isEqual(temp, target)) {
                res.add(i);
            }
            sb.deleteCharAt(0);
            if (j < n) sb.append(sArr[j]);
            i++;
            j++;
        }
        
        return res;
    }
    
    private int[] getAnagram(String s) {
        int[] sArr = new int[26];
        
        for (char c : s.toCharArray()) {
            sArr[c - 'a']++;
        }
        
        return sArr;
    }
    
    private boolean isEqual(int[] a, int[] b) {
        for (int i = 0; i < 26; i++) {
            if (a[i] != b[i]) return false;
        }
        
        return true;
    }
}