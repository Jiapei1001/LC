/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
public class Codec {

    // Encodes a tree to a single string.
    public String serialize(TreeNode root) {
        if (root == null) return "";
        
        StringBuilder sb = new StringBuilder();
        
        helper(root, sb);
        
        return sb.toString();
    }
    
    private void helper(TreeNode root, StringBuilder sb) {
        if (root == null) {
            return;
        }
        sb.append(root.val).append(",");
        helper(root.left, sb);
        helper(root.right, sb);
    }

    // Decodes your encoded data to tree.
    public TreeNode deserialize(String data) {
        if (data == null || data.length() == 0) {
            return null;
        }
        
        LinkedList<String> list = new LinkedList<>();
        
        String[] sArr = data.split(",");
        for (String s : sArr) list.add(s);
        
        return deserializeHelper(list, -1, 100000);
    }
    
    private TreeNode deserializeHelper(LinkedList<String> list, Integer min, Integer max) {
        if (list == null || list.size() == 0) return null;
        
        String first = list.get(0);
        int firstNum = Integer.parseInt(first);
        
        if (firstNum < min || firstNum > max) {
            return null;
        }
        
        TreeNode curr = new TreeNode(firstNum);
        list.remove(0);
        
        curr.left = deserializeHelper(list, min, firstNum);
        curr.right = deserializeHelper(list, firstNum, max);
        
        return curr;
    }
}

// Your Codec object will be instantiated and called as such:
// Codec ser = new Codec();
// Codec deser = new Codec();
// String tree = ser.serialize(root);
// TreeNode ans = deser.deserialize(tree);
// return ans;