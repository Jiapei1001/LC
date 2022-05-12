class Solution {
    public int[] topKFrequent(int[] nums, int k) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int i : nums) {
            map.put(i, map.getOrDefault(i, 0) + 1);
        }
        
        // priority queue
        Queue<Integer> heap = new PriorityQueue<>((n1, n2) -> map.get(n1) - map.get(n2));
        
        for (int n : map.keySet()) {
            heap.add(n);
            if (heap.size() > k) heap.poll();
        }
        
        // int[] top = new int[k];
        // for (int i = k - 1; i >= 0; i--) {
        //     top[i] = heap.poll();
        // }
        // return top;
        
        List<Integer> res = new ArrayList<>();
        for (int i = k - 1; i >= 0; i--) {
            res.add(heap.poll());
        }
        
        return res.stream().mapToInt(i -> i).toArray();
    }
}