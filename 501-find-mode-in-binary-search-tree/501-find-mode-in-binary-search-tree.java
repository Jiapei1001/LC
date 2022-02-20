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
    List<Integer> res;
    Integer prev = null;
    int acc = 0;
    int maxAcc = 0;
    
    public int[] findMode(TreeNode root) {
        res = new ArrayList<>();
        
        inorder(root);
        
        return res.stream().mapToInt(a -> a).toArray();
    }
    
    private void inorder(TreeNode root) {
        if (root == null) return;
        
        inorder(root.left);
        
        // process current node
        int currVal = root.val;
        
        if (prev == null || currVal != prev) {
            acc = 1;
        }else if (currVal == prev) {
            acc++;
        }
        
        if (acc == maxAcc) {
            res.add(currVal);
        } else if (acc > maxAcc) {
            res.clear();
            res.add(currVal);
        }
        
        maxAcc = Math.max(maxAcc, acc);
        prev = currVal;
        
        inorder(root.right);
    }
    
    
    
    // Iterative
    /*
    public int[] findMode(TreeNode root) {
        
        List<Integer> res = new ArrayList<>();
        
        Stack<TreeNode> stack = new Stack<>();
        TreeNode curr = root;
        
        Integer prev = null;
        int acc = 0;
        int maxAcc = 0;
        
        while (!stack.isEmpty() || curr != null) {
            while (curr != null) {
                stack.push(curr);
                curr = curr.left;
            }
            
            curr = stack.pop();
            int currVal = curr.val;
            
            if (prev == null || currVal != prev) {
                acc = 1;
            } else {
                acc++;
            }
            
            if (acc == maxAcc) {
                res.add(currVal);
            } else if (acc > maxAcc) {
                res.clear();
                res.add(currVal);
            }
            
            maxAcc = Math.max(maxAcc, acc);

            prev = currVal;
            curr = curr.right;
        }
        
        return res.stream().mapToInt(i -> i).toArray();
    }
    */
    
    
    /*
    Integer prev;
    int count = 1;
    int max = 0;
    
    public int[] findMode(TreeNode root) {
        if (root == null) return new int[0];
        
        List<Integer> list = new ArrayList<>();
        inorder(root, list);
        
        int[] res = new int[list.size()];
        
        int idx = 0;
        for (int i = 0; i < list.size(); i++) {
            res[idx++] = list.get(i);
        }
        
        return res;
    }
    
    private void inorder(TreeNode root, List<Integer> list) {
        if (root == null) {
            return;
        }
        
        inorder(root.left, list);
        
        if (prev != null) {
            if (root.val == prev) {
                count++;
            } else {
                count = 1;
            }
        }
        
        if (count > max) {
            max = count;
            list.clear();
            list.add(root.val);
        } else if (count == max) {
            list.add(root.val);
        }
        
        prev = root.val;
        
        inorder(root.right, list);
    }
    */
}