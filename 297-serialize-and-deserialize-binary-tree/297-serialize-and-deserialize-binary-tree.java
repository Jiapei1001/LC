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
    
    int maxHeight = 0;
    // preorder
    public String serialize(TreeNode root) {        
        if (root == null) return "#,";
        
        String curr = String.valueOf(root.val) + ",";
        String left = serialize(root.left);
        String right = serialize(root.right);
        
        return curr + left + right;
    }

    
    public TreeNode deserialize(String data) {
        LinkedList<String> list = new LinkedList<>();
        for (String c : data.split(",")) {
            list.add(c);
        }
        
        return deserializeHelper(list);
    }
    
    private TreeNode deserializeHelper(LinkedList<String> list) {
        if (list.size() == 0) return null;
        
        String first = list.removeFirst();
        if (first.equals("#")) return null;
        
        TreeNode curr = new TreeNode(Integer.parseInt(first));
        curr.left = deserializeHelper(list);
        curr.right = deserializeHelper(list);
        
        return curr;
    }
    
    
    
    /*
    private int height = 0;
    
    public String serialize(TreeNode root) {
        if (root == null) {
            return "[]";
        }
        
        // preorder traversal
        StringBuilder sb = new StringBuilder();
        sb.append("[");
                
        dfs(root, sb);
        
        sb.setLength(sb.length() - 1);
        sb.append("]");
        return sb.toString();
    }
    
    private void dfs(TreeNode root, StringBuilder sb) {
        if (root == null) {
            sb.append("null").append(",");
            return;
        }
        
        sb.append(root.val).append(",");
        dfs(root.left, sb);
        dfs(root.right, sb);
    }
    
    private int getHeight(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int left = getHeight(root.left);
        int right = getHeight(root.right);
        
        return 1 + Math.max(left, right);
    }
    
    
    // Decodes your encoded data to tree.
    public TreeNode deserialize(String data) {
        if (data == null || data.length() == 0) {
            return null;
        }
        
        if (data.equals("[]")) {
            return null;
        }
        
        String[] list = data.trim().substring(1, data.length() - 1).split(",");
     
        List<String> alist = new LinkedList<>(Arrays.asList(list));
        return deserializeHelper(alist);
    }
    
    private TreeNode deserializeHelper(List<String> alist) {
        if (alist.get(0).equals("null")) {
            alist.remove(0);
            return null;
        }
        
        TreeNode root = new TreeNode(Integer.valueOf(alist.get(0)));
        alist.remove(0);
        
        root.left = deserializeHelper(alist);
        root.right = deserializeHelper(alist);
        
        return root;
    }
    */

    // Encodes a tree to a single string.
    // public String serialize(TreeNode root) {
    //     Stack<TreeNode> deque = new Stack<>();
    //     TreeNode curr = root;
    //     deque.push(curr);
    //     String s = "";
    //     while (!deque.isEmpty()) {
    //         curr = deque.pop();
    //         if (curr == null) {
    //             s += "null,";
    //         } else {
    //             s += String.valueOf(curr.val) + ",";
    //             deque.push(curr.right);
    //             deque.push(curr.left);
    //         }
    //     }
    //     return s;
    // }
    
    
    /*
    public String serializeHelper(TreeNode root, String str) {
        if (root == null) {
            str += "null,";
        } else {
            str += String.valueOf(root.val) + ",";
            str = this.serializeHelper(root.left, str);
            str = this.serializeHelper(root.right, str);
        }
        return str;
    }
    
    public String serialize(TreeNode root) {
        return this.serializeHelper(root, "");
    }
    
    private TreeNode deserializeHelper(List<String> l) {
        if (l.get(0).equals("null")) {
            l.remove(0);
            return null;
        }
        
        TreeNode root = new TreeNode(Integer.valueOf(l.get(0)));
        l.remove(0);
        root.left = this.deserializeHelper(l);
        root.right = this.deserializeHelper(l);
        
        return root;
    }
    

    // Decodes your encoded data to tree.
    public TreeNode deserialize(String data) {
        String[] data_array = data.split(",");
        List<String> data_list = new LinkedList<>(Arrays.asList(data_array));
        return this.deserializeHelper(data_list);
    }
    */
}

// Your Codec object will be instantiated and called as such:
// Codec ser = new Codec();
// Codec deser = new Codec();
// TreeNode ans = deser.deserialize(ser.serialize(root));