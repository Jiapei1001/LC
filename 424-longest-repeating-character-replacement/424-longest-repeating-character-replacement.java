class Solution {
    public int characterReplacement(String s, int k) {
        // it's hard to control which Character is the maximum one in the window
        // thus we need to use bucket sort to decide if the operations can meet requirement
        
        // how many operations are needed = total length - Character with most count
        
        // template
        int n = s.length();
        
        char[] sArr = s.toCharArray();
        int[] bucket = new int[26];
        
        int res = 0;
        
        int i = 0;
        for (int j = 0; j < n; j++) {
            bucket[sArr[j] - 'A']++;
            
            // NOTE: here must make sure i < j !!
            // NOTE: 因为bucket里面的数量是不断变化的，因此这个helper method一定要放在条件句内部！！
            // 不可以单独抽出来！！
            while ((j - i + 1) - getCharWithMostCount(bucket) > k) {
                bucket[sArr[i] - 'A']--;
                i++;
            }
            
            res = Math.max(res, j - i + 1);
        }
        
        return res;
    }
    
    private int getCharWithMostCount(int[] bucket) {
        int res = 0;
        for (int i : bucket) res = Math.max(res, i);
        return res;
    }
}