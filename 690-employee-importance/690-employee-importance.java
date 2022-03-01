/*
// Definition for Employee.
class Employee {
    public int id;
    public int importance;
    public List<Integer> subordinates;
};
*/

class Solution {
    public int getImportance(List<Employee> employees, int id) {
        // find employee
        // get the total subtree sum of importances
        Employee t = null;
        Map<Integer, Employee> map2E = new HashMap<>();
        
        for (Employee e : employees) {
            if (e.id == id) {
                t = e;
            }
            map2E.put(e.id, e);
        }
        
        if (t == null) return Integer.MIN_VALUE;
        
        return dfs(t, map2E);
    }
    
    private int dfs(Employee t, Map<Integer, Employee> map2E) {
        if (t == null || !map2E.containsKey(t.id)) {
            return 0;
        }
        
        int curr = t.importance;
        
        if (t.subordinates == null || t.subordinates.isEmpty()) {
            return curr;
        }
        
        for (Integer i : t.subordinates) {
            curr += dfs(map2E.get(i), map2E);
        }
        
        return curr;
    }
}