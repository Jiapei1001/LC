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
    
    public List<String> binaryTreePaths(TreeNode root) {
        List<String> res = new ArrayList<>();
        dfs(root, "", res);
        
        return res;
    }
    
    private void dfs(TreeNode root, String curr, List<String> res) {
        if (root == null) return;
        // leaf
        if (root.left == null && root.right == null) {
            res.add(curr + root.val);
            return;
        }
        
        dfs(root.left, curr + root.val + "->", res);
        dfs(root.right, curr + root.val + "->", res);
    }
    
    
    
    /*
    public List<String> binaryTreePaths(TreeNode root) {
        List<String> res = new ArrayList<>();
        
        dfs(root, "", res);
        
        return res;
    }
    
    private void dfs(TreeNode root, String curr, List<String> res) {
        if (root.left == null && root.right == null) {
            res.add(curr + root.val);
            return;
        }
        
        if (root.left != null) {
            dfs(root.left, curr + root.val + "->", res);
        }
        if (root.right != null) {
            dfs(root.right, curr + root.val + "->", res);
        }
        
        return;
    }
    */
    
    
    /*
    public List<String> binaryTreePaths(TreeNode root) {
        List<String> res = new ArrayList<>();
        
        if (root == null) return res;
    
        StringBuilder sb = new StringBuilder();
        dfs(root, sb, res);
        
        return res;
    }
    
    private void dfs(TreeNode root, StringBuilder sb, List<String> res) {
        if (root == null) {
            return;
        }
        
        // 如果使用StringBuilder，那么一定要使用temp来记录之前的长度
        int temp = sb.length();
        
        if (root.left == null && root.right == null) {
            sb.append(root.val);
            res.add(sb.toString());
            
            // 配合temp使用
            sb.delete(temp, sb.length());
            return;
        }
        
        sb.append(root.val).append("->");
        dfs(root.left, sb, res);
        dfs(root.right, sb, res);
        
        // 配合temp使用
        sb.delete(temp, sb.length());
    }
    */
    
    
    /*
    public List<String> binaryTreePaths(TreeNode root) {
        List<String> res = new ArrayList<>();
        
        if (root == null) return res;
        
        dfs(root, "", res);
        
        return res;
    }
    
    private void dfs(TreeNode root, String s, List<String> res) {
        if (root == null) {
            return;
        }
        
        if (root.left == null && root.right == null) {
            s += root.val;
            res.add(s);
            return;
        }
        
        dfs(root.left, s + root.val + "->", res);
        dfs(root.right, s + root.val + "->", res);
    }
    */
}