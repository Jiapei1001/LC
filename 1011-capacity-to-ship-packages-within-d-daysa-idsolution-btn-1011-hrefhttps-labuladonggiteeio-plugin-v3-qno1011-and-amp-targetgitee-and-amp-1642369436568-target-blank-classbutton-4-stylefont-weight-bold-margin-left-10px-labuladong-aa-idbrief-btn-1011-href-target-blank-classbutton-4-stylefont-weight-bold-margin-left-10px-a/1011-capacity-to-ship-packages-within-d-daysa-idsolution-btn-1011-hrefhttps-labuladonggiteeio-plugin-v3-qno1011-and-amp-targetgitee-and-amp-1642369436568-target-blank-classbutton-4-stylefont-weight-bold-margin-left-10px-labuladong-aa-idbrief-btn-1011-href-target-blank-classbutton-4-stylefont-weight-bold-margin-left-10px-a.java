class Solution {
    public int shipWithinDays(int[] weights, int days) {
        // the max possible weight capacity is the sum
        // it only takes one day to ship
        int l = Integer.MIN_VALUE, r = 0;
        for (int w : weights) {
            l = Math.max(l, w);
            r += w;
        }
        
        while (l + 1 < r) {
            int mid = l + (r - l) / 2;
            
            if (isAbleToShipWithinDays(weights, mid, days)) {
                r = mid;
            } else {
                l = mid;
            }
        }
        
        if (isAbleToShipWithinDays(weights, l, days)) {
            return l;
        }
        if (isAbleToShipWithinDays(weights, r, days)) {
            return r;
        }
        
        return -1;
    }
    
    private boolean isAbleToShipWithinDays(int[] weights, int mid, int days) {
        // NOTE: 这里从第一天开始计时，因此起始值是1
        int count = 1;
        
        int temp = 0;
        
        for (int w : weights) {
            if (temp + w > mid) {
                count++;
                temp = 0;
            }
            
            temp += w;
        }
        
        /*
        int i = 0;
        while (i < weights.length) {
            temp += weights[i];
            
            if (temp <= mid) {
                i++;
            } else {
                count++;
                temp = 0;
            }
        }
        */
        
        return count <= days;
    }
}