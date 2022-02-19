/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode() {}
 *     TreeNode(int val) { this.val = val; }
 *     TreeNode(int val, TreeNode left, TreeNode right) {
 *         this.val = val;
 *         this.left = left;
 *         this.right = right;
 *     }
 * }
 */
class Solution {
    
    int res = 0;
    
    public int pathSum(TreeNode root, int targetSum) {
        dfs(root, targetSum);
        return res;
    }
    
    private List<Integer> dfs(TreeNode root, int targetSum) {
        if (root == null) {
            return new ArrayList<Integer>();
        }
        
        List<Integer> level = new ArrayList<>();
        level.add(root.val);
        // NOTE: here also need to check if root.val == targetSum
        if (root.val == targetSum) res++;
        
        List<Integer> left = dfs(root.left, targetSum);
        List<Integer> right = dfs(root.right, targetSum);
        
        for (int i : left) {
            if (root.val + i == targetSum) res++;
            level.add(root.val + i);
        }
        for (int i : right) {
            if (root.val + i == targetSum) res++;
            level.add(root.val + i);
        }
        
        return level;
    }
    
    
    
    /*
    // PrefixSum + SumMap with Counter
    private int res = 0;
    
    public int pathSum(TreeNode root, int targetSum) {
        Map<Integer, Integer> sumMap = new HashMap<>();
        
        dfs(root, 0, targetSum, sumMap);
        
        return res;
    }
    
    private void dfs(TreeNode root, 
                     int currSum, 
                     int targetSum, 
                     Map<Integer, Integer> sumMap) {
        if (root == null) {
            return;
        }
        
        // prefixSum
        currSum += root.val;
        
        // Situation 1: prefixSum directly == targetSum
        if (currSum == targetSum) {
            res++;
        }
        
        // Situation 2: check if prefixSum - targetSum exists
        res += sumMap.getOrDefault(currSum - targetSum, 0);
        
        // update counter
        sumMap.put(currSum, sumMap.getOrDefault(currSum, 0) + 1);
        
        dfs(root.left, currSum, targetSum, sumMap);
        dfs(root.right, currSum, targetSum, sumMap);
        
        // NOTE: must clear the currSum from the sumMap
        // as it is not used, following the recursion
        sumMap.put(currSum, sumMap.get(currSum) - 1);
    }
    */
    
    
    
    
    // DFS
    /*
    private int res = 0;
    
    public int pathSum(TreeNode root, int targetSum) {
        
        dfs(root, targetSum);
        
        return res;
    }
    
    private List<Integer> dfs(TreeNode root, int targetSum) {
        if (root == null) {
            return new ArrayList<Integer>();
        }
        
        List<Integer> newSet = new ArrayList<>();
        // NOTE: must add current value to the list!!
        newSet.add(root.val);
        // NOTE: must check if curr single node's val == targetSum
        // as below check the combination of curr node with others
        if (root.val == targetSum) res++;
        
        List<Integer> left = dfs(root.left, targetSum);
        for (int i : left) {
            int curr = root.val + i;
            if (curr == targetSum) res++;
            newSet.add(curr);
        }
        
        List<Integer> right = dfs(root.right, targetSum);
        for (int i : right) {
            int curr = root.val + i;
            if (curr == targetSum) res++;
            newSet.add(curr);
        }
        
        return newSet;
    }
    */
}