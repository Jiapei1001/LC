class Solution {
    
    public int minEatingSpeed(int[] piles, int h) {
        int n = piles.length;
        
        // get max count of bananas
        int r = 0;
        for (int i : piles) r = Math.max(r, i);
        
        int l = 1;
        r = r + 1;
        
        while (l + 1 < r) {
            int mid = l + (r - l) / 2;
            
            if (isAbleFinishEatingWithinHours(piles, mid, h)) {
                r = mid;
            } else {
                l = mid;
            }
        }
        
        if (isAbleFinishEatingWithinHours(piles, l, h)) return l;
        if (isAbleFinishEatingWithinHours(piles, r, h)) return r;
        
        return -1;
    }
    
    private boolean isAbleFinishEatingWithinHours(int[] piles, int mid, int h) {
        int hours = 0;
        
        for (int num : piles) {
            int takenHour = num / mid;
            
            if (num % mid == 0) hours += takenHour;
            else hours += (takenHour + 1);
        }
        
        return hours <= h;
    }
}