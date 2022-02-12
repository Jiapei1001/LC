class Solution {
    public String removeDuplicateLetters(String s) {
        // increasing stack,
        // when is not the last appeared index, pop
        // if it is the last appeared index, don't pop
        int n = s.length();
        char[] sArr = s.toCharArray();
        
        int[] lastIdx = new int[26];
        
        int uniqueCnt = 0;
        for (int i = 0; i < n; i++) {
            lastIdx[sArr[i] - 'a'] = i;
        }
        
        Stack<Integer> stack = new Stack<>();
        Set<Character> visited = new HashSet<>();
        
        for (int i = 0; i < n; i++) {
            if (visited.contains(sArr[i])) {
                continue;
            }
            
            while (!stack.isEmpty() && 
                   (// increasing stack
                    sArr[i] <= sArr[stack.peek()] && 
                    // make sure it is not the last char
                    // NOTE: 这里是和curr i相比较，stack.peek()和lastIdx相比较
                    // 这里保证的stack.peek()如果是最后一位，同时在curr i之前，那么不可以被pop掉
                    i < lastIdx[sArr[stack.peek()] - 'a'])) {
                int last = stack.pop();
                visited.remove(sArr[last]);
            }
            
            visited.add(sArr[i]);
            stack.push(i);
        }
        
        StringBuilder sb = new StringBuilder();
        while (!stack.isEmpty()) sb.append(sArr[stack.pop()]);
        
        return sb.reverse().toString();
    }
}