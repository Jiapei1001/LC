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
    public List<TreeNode> delNodes(TreeNode root, int[] to_delete) {
        // mark as -1
        Set<Integer> delete = new HashSet<>();
        for (int i : to_delete) delete.add(i);
        inorder(root, delete);
        
        List<TreeNode> res = new ArrayList<>();
        dfs(false, root, res);
        
        return res;
    }
    
    private TreeNode dfs(boolean hasParent, TreeNode curr, List<TreeNode> res) {
        if (curr == null) return null;
        
        boolean delete = curr.val == -1;
        
        if (!hasParent && !delete) res.add(curr);
        
        curr.left = dfs(!delete, curr.left, res);
        curr.right = dfs(!delete, curr.right, res);
        
        return delete ? null : curr;
    }
    
    private void inorder(TreeNode root, Set<Integer> delete) {
        if (root == null) return;
        
        if (delete.contains(root.val)) {
            root.val = -1;
        }
        inorder(root.left, delete);
        inorder(root.right, delete);
    }
}