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
    
    public int sumNumbers(TreeNode root) {
        helper(root, "");
        
        return res;
    }
    
    private void helper(TreeNode root, String prev) {
        if (root == null) return;
        
        // not going to the null value, as it will make the sum * twice
        if (root.left == null && root.right == null) {
            String curr = prev + root.val;
            res += Integer.parseInt(curr);
            return;
        }
        
        helper(root.left, prev + root.val);
        helper(root.right, prev + root.val);
    }
    
    
    /*
    public int sumNumbers(TreeNode root) {
        if (root == null) return 0;
        
        List<Integer> path = new ArrayList<>();
        List<Integer> list = new ArrayList<>();
        dfs(root, path, list);
        
        int res = 0;
        for (int i : list) res += i;
        
        // each node was counted twice
        return res;
    }
    
    private void dfs(TreeNode root, List<Integer> path, List<Integer> list) {
        if (root == null) {
            return;
        }
        
        if (root.left == null && root.right == null) {
            int num = 0;
            for (int i = 0; i < path.size(); i++) {
                int j = path.size() - i;
                num += path.get(i) * Math.pow(10, j);
            }
            num += root.val;
            
            list.add(num);
            return;
        }
        
        path.add(root.val);
        
        dfs(root.left, path, list);
        dfs(root.right, path, list);
        
        path.remove(path.size() - 1);
    }
    */
}