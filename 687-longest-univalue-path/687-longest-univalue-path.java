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
    
    int maxSeen = 1;
    
    public int longestUnivaluePath(TreeNode root) {
        helper(root);
        
        return maxSeen - 1;
    }
    
    private int[] helper(TreeNode root) {
        if (root == null) {
            return new int[]{-1000000, 0};
        }
        
        int[] left = helper(root.left);
        int[] right = helper(root.right);
        
        if (root.val == left[0] && root.val == right[0]) {
            maxSeen = Math.max(maxSeen, 1 + left[1] + right[1]);
            return new int[]{root.val, 1 + Math.max(left[1], right[1])};
        } else if (root.val == left[0]) {
            maxSeen = Math.max(maxSeen, 1 + left[1]);
            return new int[]{root.val, 1 + left[1]};
        } else if (root.val == right[0]) {
            maxSeen = Math.max(maxSeen, 1 + right[1]);
            return new int[]{root.val, 1 + right[1]};
        } else {
            return new int[]{root.val, 1};
        }
    }
    
    
    /*
    int longest = 0;
    
    public int longestUnivaluePath(TreeNode root) {
        helper(root);
        
        return longest;
    }
    
    private int helper(TreeNode root) {
        if (root == null) {
            return 0;
        }
        
        int left = helper(root.left);
        int right = helper(root.right);
        
        // NOTE: if not the same, then return 0, to the global recursion
        int leftSame = 0, rightSame = 0;
        
        if (root.left != null && root.val == root.left.val) {
            leftSame += left + 1;
        }
        
        if (root.right != null && root.val == root.right.val) {
            rightSame += right + 1;
        }
        
        longest = Math.max(longest, leftSame + rightSame);
        
        return Math.max(leftSame, rightSame);
    }
    
    
    /* 自己做的错了
    int longest = 0;
    
    public int longestUnivaluePath(TreeNode root) {
        helper(root);
        
        return longest;
    }
    
    private int helper(TreeNode root) {
        if (root == null) {
            return 0;
        }
        
        int left = helper(root.left);
        int right = helper(root.right);
        
        if (root.left != null && root.val == root.left.val && 
            root.right != null && root.val == root.right.val) {
            longest = Math.max(longest, left + right + 2);
            
            return 1 + Math.max(left, right);
        } else if (root.left != null && root.val == root.left.val) {
            longest = Math.max(longest, left + 1);
            
            return Math.max(1 + left, right);
        } else if (root.right != null && root.val == root.right.val) {
            longest = Math.max(longest, right + 1);
            
            return Math.max(1 + right, left);
        } else {
            longest = Math.max(longest, Math.max(left, right));
            
            return 0;
        }
    }
    */
}