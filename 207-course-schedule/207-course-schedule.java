class Solution {
    
    public boolean canFinish(int numCourses, int[][] prerequisites) {
        if (numCourses == 0 || prerequisites.length == 0) {
            return true;
        }
        
        // build graph
        Map<Integer, Set<Integer>> graph = new HashMap<>();
        Map<Integer, Integer> indegree = new HashMap<>();
        
        for (int[] p : prerequisites) {
            graph.computeIfAbsent(p[1], a -> new HashSet<Integer>()).add(p[0]);
            indegree.put(p[0], indegree.getOrDefault(p[0], 0) + 1);
        }
        
        Queue<Integer> q = new LinkedList<>();
        
        for (int i = 0; i < numCourses; i++) {
            indegree.putIfAbsent(i, 0);
        }
        
        
        for (int k : indegree.keySet()) {
            if (indegree.get(k) == 0) {
                q.offer(k);
            }
        }
        
        int visitedCourse = 0;
        while (!q.isEmpty()) {
            int curr = q.poll();
            visitedCourse++;
            
            if (!graph.containsKey(curr)) {
                continue;
            }
            
            for (int next : graph.get(curr)) {
                indegree.put(next, indegree.get(next) - 1);
                
                if (indegree.get(next) == 0) {
                    q.offer(next);
                }
            }
        }
        
        // if (visitedCourse < numCourses) {
        //     return false;
        // }
        
        return visitedCourse == numCourses;
    }


//     static int WHITE = 1, GRAY = 2, BLACK = 3;
//     boolean isPossible = true;
//     Map<Integer, Integer> intColors;
//     Map<Integer, List<Integer>> adjList;
    
//     private void init(int numCourses) {
//         this.intColors = new HashMap<Integer, Integer>();
//         this.adjList = new HashMap<Integer, List<Integer>>();
        
//         for (int i = 0; i < numCourses; i++) {
//             this.intColors.put(i, WHITE);
//         }
//     }
    
    
//     private void bfs(int node) {
//         if (!isPossible) return;
        
//         this.intColors.put(node, GRAY);
        
//         for (Integer neighbor : this.adjList.getOrDefault(node, new ArrayList<Integer>())) {
//             if (this.intColors.get(neighbor) == WHITE) {
//                 this.bfs(neighbor);
//             } else if (this.intColors.get(neighbor) == GRAY) {
//                 this.isPossible = false;
//             }
//         }
        
//         this.intColors.put(node, BLACK);
//     }
    
    
    
//     public boolean canFinish(int numCourses, int[][] prerequisites) {
        
//         // init variables and colors
        
//         // get adjacent list
        
//         // loop through and check if there is a circle (as white)
        
//         this.init(numCourses);
        
//         for (int i = 0; i < prerequisites.length; i++) {
//             int src = prerequisites[i][1];
//             int dest = prerequisites[i][0];
            
//             List<Integer> lst = this.adjList.getOrDefault(src, new ArrayList<Integer>());
            
//             lst.add(dest);
//             this.adjList.put(src, lst);
//         }
        
//         for (int i = 0; i < numCourses; i++) {
//             if(this.intColors.get(i) == WHITE) {
//                 this.bfs(i);
//             }
//         }
        
//         return this.isPossible;
//     }
    
    
    /*
    public boolean canFinish(int numCourses, int[][] prerequisites) {
        if (numCourses == 0 || prerequisites.length == 0) {
            return true;
        }
        ArrayList<Integer> result = new ArrayList<>();

        
        Map<Integer, List<Integer>> map = new HashMap<>();
        Map<Integer, Integer> indegree = new HashMap<>();
        for (int[] course : prerequisites) {
            List<Integer> temp = map.getOrDefault(course[1], new ArrayList<Integer>());
            temp.add(course[0]);
            map.put(course[1], temp);
            indegree.put(course[0], indegree.getOrDefault(course[0], 0) + 1);
        }
        
        Queue<Integer> queue = new LinkedList<>();
        for (int i = 0; i < numCourses; i++) {
            if (!indegree.containsKey(i)) {
                queue.offer(i);
            }
        }
        
        while (!queue.isEmpty()) {
            Integer curr = queue.poll();
            result.add(curr);
            if (!map.containsKey(curr)) continue;
            for (int childCourse : map.get(curr)) {
                indegree.put(childCourse, indegree.get(childCourse) - 1);
                if (indegree.get(childCourse) == 0) {
                    queue.offer(childCourse);
                }
            }
        }
        
        return result.size() == numCourses;  
    }
    */
}
