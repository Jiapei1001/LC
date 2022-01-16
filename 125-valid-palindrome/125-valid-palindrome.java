class Solution {
    
    public boolean isPalindrome(String s) {
        if (s == null || s.length() == 0) return true;
        
        s = s.toLowerCase().replaceAll("\\s", "").replaceAll("[^A-Za-z0-9]", "");
        
        System.out.println(s);
        
        if (s == null || s.length() == 0) return true;
        
        int i = 0, j = s.length() - 1;
        
        while (i <= j) {
            if (s.charAt(i++) != s.charAt(j--)) {
                return false;
            }
        }
        
        return true;
    }
}