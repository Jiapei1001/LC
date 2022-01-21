/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
class Solution {
    
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null) {
            return root;
        }
        
        TreeNode left = lowestCommonAncestor(root.left, p, q);
        TreeNode right = lowestCommonAncestor(root.right, p, q);
        
        if ((root == p || root == q) && (left != null || right != null)) {
            return root;
        }
        
        if (root == p || root == q) {
            return root;
        }
        
        if (left != null && right != null) {
            return root;
        }
        
        return left == null ? right : left;
    }
    
    
    
    
    /*
    // Iterative
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        Map<TreeNode, TreeNode> parent = new HashMap<>();
        
        dfs(root, null, parent);
        
        Set<TreeNode> path = new HashSet<>();
        
        // get all parent path from p to root
        while (p != null) {
            path.add(p);
            p = parent.get(p);
        }
        
        // get the first node that contains both p and q
        while (!path.contains(q)) {
            q = parent.get(q);
        }
        
        // rn, q is the parent node
        return q;
    }
    
    
    private void dfs(TreeNode root, TreeNode pa, Map<TreeNode, TreeNode> parent) {
        if (root == null) {
            return;
        }
        
        parent.put(root, pa);
        dfs(root.left, root, parent);
        dfs(root.right, root, parent);
    }
    */
    
    
    // Recursion
    /*
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        return helper(root, p, q);
    }
    
    private TreeNode helper(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null || root == p || root == q) {
            return root;
        }
        
        TreeNode left = helper(root.left, p, q);
        TreeNode right = helper(root.right, p, q);
        
        // 找到root，另一个在左边，或者右边
        if ((root == p || root == q) && (left != null || right != null)) {
            return root;
        }
        
        // lowest ancestor找到
        if (left != null && right != null) {
            return root;
        }
        
        // 这一步check right可以被简化为，只要不是left，那么必定是right
        // return (root == p || root == q) ? root : (left == null ? (right == null ? null : right) : left);
        
        // 这一步 (root == p || root == q) 可以放到base case
        // return (root == p || root == q) ? root : (left == null ? right : left);
        
        // finally，现在的root不是，那么lowest ancestor不在左边，就在右边
        return left == null ? right : left;
    }
    */
    
    
        
        
        
        
        
    // public boolean contains(TreeNode root, TreeNode target) {
    //     if (root == null) return false;
    //     if (root.val == target.val) return true;
    //     return this.contains(root.left, target) || this.contains(root.right, target);
    // }
    /*
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null) return null;
        
        if (root.val == p.val || root.val == q.val) return root;
        
        TreeNode leftAncestor = this.lowestCommonAncestor(root.left, p, q);
        TreeNode rightAncestor = this.lowestCommonAncestor(root.right, p, q);
        
        // 其中一侧
        if (leftAncestor == null) return rightAncestor;
        if (rightAncestor == null) return leftAncestor;
        
        // 一个在左，一个在右
        // if (leftAncestor!= null && rightAncestor != null) 
        return root;
        
        
        
//         if (root.val == p.val && this.contains(root, q)) {
//             return root;
//         } else if (root.val == q.val && this.contains(root, p)) {
//             return root;
//         } else if (this.contains(root.left, p) && this.contains(root.right, q)) {
//             return root;
//         } else if (this.contains(root.right, p) && this.contains(root.left, q)) {
//             return root;
//         }

//         if (root.left != null) return this.lowestCommonAncestor(root.left, p, q);
//         if (root.right != null) return this.lowestCommonAncestor(root.right, p, q);
        
        // if (root.val == p.val && this.contains(root, q)) {
        //     return root;
        // } else if (root.val == q.val && this.contains(root, p)) {
        //     return root;
        // } else if (this.contains(root.left, p) && this.contains(root.right, q)) {
        //     return root;
        // } else if (this.contains(root.right, p) && this.contains(root.left, q)) {
        //     return root;
        // } else if (this.contains(root.left, p) && this.contains(root.left, q)) {
        //     return this.lowestCommonAncestor(root.left, p, q);
        // } else if (this.contains(root.right, p) && this.contains(root.right, q)) {
        //     return this.lowestCommonAncestor(root.right, p, q);
        // }
        
        // return null;
    }
    */
}
