class Solution {
    
    public boolean isLongPressedName(String name, String typed) {
        int n = name.length();
        int m = typed.length();
        
        if (m < n) return false;
        
        char[] xArr = name.toCharArray();
        char[] yArr = typed.toCharArray();
        
        int i = 0, j = 0;
        
        while (i < n && j < m) {
            if (xArr[i] != yArr[j]) return false;
            // update same i
            while (i + 1 < n && xArr[i] == xArr[i + 1]) {
                i++;
                j++;
                if (xArr[i] != yArr[j]) return false;
            }
            // update same j
            while (j + 1 < m && yArr[j] == yArr[j + 1]) {
                j++;
            }
            i++;
            j++;
        }
        
        return i == n && j == m;
    }
    
    
    /*
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
    */
}