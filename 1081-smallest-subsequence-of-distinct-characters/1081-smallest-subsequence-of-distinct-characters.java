class Solution {
    public String smallestSubsequence(String s) {
        // increasing stack
        // make sure char in stack is visited only once
        // make sure it is not the last index
        int n = s.length();
        char[] sArr = s.toCharArray();
        
        int[] count = new int[26];
        for (char c : sArr) count[c - 'a']++;
        
        Set<Character> visited = new HashSet<>();
        Stack<Character> stack = new Stack<>();
        
        for (char c : sArr) {
            // must -1
            count[c - 'a']--;
            
            // has already inside stack
            if (visited.contains(c)) {
                continue;
            }
            
            while (!stack.isEmpty() && stack.peek() > c && count[stack.peek() - 'a'] > 0) {
                char last = stack.pop();
                visited.remove(last);
            }
            
            stack.push(c);
            visited.add(c);
        }
        
        StringBuilder sb = new StringBuilder();
        while (!stack.isEmpty()) sb.append(stack.pop());
        
        return sb.reverse().toString();
    }
}