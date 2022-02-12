class Solution {
    public int[] nextGreaterElement(int[] nums1, int[] nums2) {
        Map<Integer, Integer> map2Greater = new HashMap<>();
        
        Stack<Integer> stack = new Stack<>();
        for (int i : nums2) {
            while (!stack.isEmpty() && stack.peek() < i) {
                int last = stack.pop();
                map2Greater.put(last, i);
            }
            stack.push(i);
        }
        
        while (!stack.isEmpty()) {
            int last = stack.pop();
            map2Greater.put(last, -1);
        }
        
        List<Integer> res = new ArrayList<>();
        for (int i : nums1) res.add(map2Greater.get(i));
        
        return res.stream().mapToInt(Integer::intValue).toArray();
    }
}