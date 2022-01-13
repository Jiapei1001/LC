class Solution {
    public boolean isLongPressedName(String name, String typed) {
        int n = name.length(), m = typed.length();
        
        int i = 0, j = 0;
        
        while (i < n && j < m) {
            char c1 = name.charAt(i), c2 = typed.charAt(j);
            
            if (c1 != c2) return false;
            
            int cnt1 = 0;
            while (i < n && name.charAt(i) == c1) {
                i++;
                cnt1++;
            }
            
            int cnt2 = 0;
            while (j < m && typed.charAt(j) == c2) {
                j++;
                cnt2++;
            }
            
            if (cnt1 > cnt2) return false;
        }
        
        return i == n && j == m;
    }
}