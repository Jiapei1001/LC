class NumArray {
    
    Node root;

    public NumArray(int[] nums) {
        this.root = buildTree(nums, 0, nums.length - 1);
    }
    
    private Node buildTree(int[] nums, int l, int r) {
        if (l > r) {
            return null;
        }
        
        Node curr = new Node(l, r, 0);
        
        if (l == r) {
            curr.val = nums[l];
        } else {
            int mid = l + (r - l) / 2;

            curr.left = buildTree(nums, l, mid);
            curr.right = buildTree(nums, mid + 1, r);
            curr.val = curr.left.val + curr.right.val;
        }
        
        return curr;
    }
    
    public void update(int index, int val) {
        this.updateTree(root, index, val);
    }
    
    public Node updateTree(Node root, int index, int val) {        
        if (root.start == index && root.end == index) {
            root.val = val;
            return root;
        }

        int mid = root.start + (root.end - root.start) / 2;
        
        if (index <= mid) {
            root.left = updateTree(root.left, index, val);
        } else if (index >= mid + 1) {
            root.right = updateTree(root.right, index, val);
        }
        
        // update value
        root.val = root.left.val + root.right.val;
        
        return root;
    }
    
    public int sumRange(int left, int right) {
        return sumRangeTree(root, left, right);
    }
    
    public int sumRangeTree(Node root, int left, int right) {
        // base case, find that range
        if (root.start == left && root.end == right) {
            return root.val;
        }
        
        int mid = root.start + (root.end - root.start) / 2;
        
        // range in right segment
        if (left >= mid + 1) {
            // NOTE: 这里千万不可以写成query (mid + 1, right)
            // 因为这里并没有切分，left，right还是细分的区间
            return sumRangeTree(root.right, left, right);
        }
        // range in left segment
        else if (right <= mid) {
            // NOTE: 这里千万不可以写成query (left，mid)
            // 因为这里并没有切分，left，right还是细分的区间
            return sumRangeTree(root.left, left, right);
        }
        // range in both side
        else {
            // NOTE：这里需要用mid和mid + 1来进行切分
            // 因为left，right已经不再精确了
            return sumRangeTree(root.left, left, mid) + 
                   sumRangeTree(root.right, mid + 1, right);
        }
    }
}


class Node {
    int start;
    int end;
    int val;
    Node left;
    Node right;
    
    public Node(int start, int end, int val) {
        this.start = start;
        this.end = end;
        this.val = 0;
    }
}

/**
 * Your NumArray object will be instantiated and called as such:
 * NumArray obj = new NumArray(nums);
 * obj.update(index,val);
 * int param_2 = obj.sumRange(left,right);
 */