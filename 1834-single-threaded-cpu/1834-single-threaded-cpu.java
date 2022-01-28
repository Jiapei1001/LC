class Solution {
    public int[] getOrder(int[][] tasks) {
        List<Task> list = new ArrayList<>();
        
        PriorityQueue<Task> pq = new PriorityQueue<>((a, b) -> {
            return a.p == b.p ? a.label - b.label : a.p - b.p;
        });
        
        for (int i = 0; i < tasks.length; i++) {
            Task temp = new Task(i, tasks[i][0], tasks[i][1]);
            list.add(temp);
        }
        
        Collections.sort(list, (a, b) -> a.s - b.s);
        
        // NOTE: 核心在于，如何找到currentTime以内的tasks，然后把这些tasks放到pq里进行排序
        int[] res = new int[tasks.length];
        int idx = 0; // result index
        int ti = 0; // current enqueued task index
        int currentTime = 0;
        
        while (idx < tasks.length) {
            // 找到currentTime以内的available tasks
            while (ti < tasks.length && list.get(ti).s <= currentTime) {
                pq.offer(list.get(ti));
                ti++;
            }
            
            if (pq.isEmpty()) {
                currentTime = list.get(ti).s;
                continue;
            }
            
            // 现在available tasks里的第一个，进行process
            Task temp = pq.poll();
            res[idx] = temp.label;
            idx++;
            
            
            // NOTE: 这里千万不可以是currentTime = temp.e！！
            // 因为temp.e可能是发生在后面，这里只需要增加process time！！
            currentTime += temp.p;
        }
        
        return res;
    }
}

class Task {
    int label;
    int s;
    int p;
    int e;
    
    public Task(int label, int s, int p) {
        this.label = label;
        this.s = s;
        this.p = p;
        this.e = s + p;
    }
}