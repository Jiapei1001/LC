class Solution {
    
    public String reverseOnlyLetters(String s) {
        int n = s.length();
        
        char[] sArr = s.toCharArray();
        
        int l = 0, r = n - 1;
        
        while (l < r) {
            while (l < r && !Character.isLetter(sArr[l])) {
                l++;
            }
            while (l < r && !Character.isLetter(sArr[r])) {
                r--;
            }
            
            if (l < r) {
                char temp = sArr[l];
                sArr[l] = sArr[r];
                sArr[r] = temp;
                
                l++;
                r--;
            }
        }
        
        return new String(sArr);
    }
}