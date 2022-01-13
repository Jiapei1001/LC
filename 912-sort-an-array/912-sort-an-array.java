class Solution {
    
    public int[] sortArray(int[] nums) {
        quickSort(nums, 0, nums.length - 1);
        
        return nums;
    }
    
    private void quickSort(int[] nums, int left, int right) {
        if (left >= right) {
            return;
        }
        
        int l = left, r = right;
        int pivot = nums[(l + r) / 2];
        
        while (l <= r) {
            
            while (l <= r && nums[l] < pivot) {
                l++;
            }
            
            while (l <= r && nums[r] > pivot) {
                r--;
            }
            
            if (l <= r) {
                int temp = nums[l];
                nums[l] = nums[r];
                nums[r] = temp;
                
                l++;
                r--;
            }
        }
        
        quickSort(nums, left, r);
        quickSort(nums, l, right);
    }
    
    
    // Merge Sort
    /*
    public int[] sortArray(int[] nums) {
        divide(nums, 0, nums.length - 1);
        
        return nums;
    }
    
    private void divide(int[] nums, int left, int right) {
        if (left >= right) {
            return;
        }
        
        int mid = left + (right - left) / 2;
        divide(nums, left, mid);
        divide(nums, mid + 1, right);
        
        merge(nums, left, right);
    }
    
    private void merge(int[] nums, int l, int r) {
        
        int n = nums.length;
        
        int[] temp = new int[r - l + 1];
        
        int mid = l + (r - l) / 2;
        int i = l, j = mid + 1;
        
        int idx = 0;
        while (i <= mid && j <= r) {
            if (nums[i] < nums[j]) {
                temp[idx++] = nums[i];
                i++;
            } else {
                temp[idx++] = nums[j];
                j++;
            }
        }
        
        while (i <= mid) {
            temp[idx++] = nums[i++];
        }
        
        while (j <= r) {
            temp[idx++] = nums[j++];
        }
        
        // Last step, copy back to the original array
        idx = 0;
        for (i = l; i <= r; i++) {
            nums[i] = temp[idx++];
        }
    }
    */
}