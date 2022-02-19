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
    
    public List<List<Integer>> pathSum(TreeNode root, int targetSum) {
        List<List<Integer>> res = new ArrayList<>();
        List<Integer> path = new ArrayList<>();
        
        dfs(root, 0, targetSum, path, res);
        
        return res;
    }
    
    private void dfs(TreeNode root, int curr, int targetSum, List<Integer> path, List<List<Integer>> res) {
        if (root == null) {
            return;
        }
        if (root.left == null && root.right == null) {
            if (curr + root.val == targetSum) {
                path.add(root.val);
                res.add(new ArrayList<Integer>(path));
                // NOTE: here must remove the last added leaf node!!
                path.remove(path.size() - 1);
            }
            // NOTE: return outside of the above condition
            return;
        }
        
        path.add(root.val);
        dfs(root.left, curr + root.val, targetSum, path, res);
        dfs(root.right, curr + root.val, targetSum, path, res);
        path.remove(path.size() - 1);
    }
    
    
    /*
    public List<List<Integer>> pathSum(TreeNode root, int targetSum) {
        List<List<Integer>> res = new ArrayList<>();
        
        List<Integer> path = new ArrayList<>();
        
        dfs(root, 0, targetSum, path, res);
        
        return res;
    }
    
    private void dfs(TreeNode root, 
                     int currSum, 
                     int targetSum, 
                     List<Integer> path, 
                     List<List<Integer>> res) {
        if (root == null) {
            return;
        }
        
        if (root.left == null && root.right == null) {
            currSum += root.val;
            if (currSum == targetSum) {
                path.add(root.val);
                res.add(new ArrayList<Integer>(path));
                path.remove(path.size() - 1);
            }
            
            return;
        }
        
        path.add(root.val);
        dfs(root.left, currSum + root.val, targetSum, path, res);
        dfs(root.right, currSum + root.val, targetSum, path, res);
        path.remove(path.size() - 1);
    }
    */
}