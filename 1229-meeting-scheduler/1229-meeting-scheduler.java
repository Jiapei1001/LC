class Solution {
    public List<Integer> minAvailableDuration(int[][] slots1, int[][] slots2, int duration) {
        List<int[]> slots = new ArrayList<>();
        
        for (int[] s : slots1) slots.add(s);
        for (int[] s : slots2) slots.add(s);
        
        // start increasing, end decreasing
        Collections.sort(slots, (a, b) -> {return a[0] == b[0] ? b[1] - a[1] : a[0] - b[0];});
        
        int[] prev = slots.get(0);
        for (int i = 1; i < slots.size(); i++) {
            if (slots.get(i)[0] < prev[1]) {
                // intersection potential
                int l = Math.max(prev[0], slots.get(i)[0]);
                int r = Math.min(prev[1], slots.get(i)[1]);
                
                if (r - l >= duration) {
                    return new ArrayList<Integer>(List.of(l, l + duration));
                }
                
                prev = prev[1] >= slots.get(i)[1] ? prev : slots.get(i);
            } else {
                prev = slots.get(i);
            }
        }
        
        return new ArrayList<Integer>();
    }
}