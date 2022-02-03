class StockSpanner {
    // monotonic stack
    // what is the inside? -> decreasing stock prices

    // if current Stock >= stack.peek();
    // then pop(), and enqueue current Stock with updated days
    // because, if future stock >= current Stock, then it must be larger than the pop stock
    
    // int[0] := price; int[1] := days
    Stack<int[]> stack;
    
    public StockSpanner() {
        this.stack = new Stack<>();
    }
    
    public int next(int price) {
        int tempDay = 1;
        
        while (!this.stack.isEmpty() && price >= this.stack.peek()[0]) {
            int[] last = this.stack.pop();
            tempDay += last[1];        
        }
        
        this.stack.push(new int[]{price, tempDay});
        
        return tempDay;
    }
}

/**
 * Your StockSpanner object will be instantiated and called as such:
 * StockSpanner obj = new StockSpanner();
 * int param_1 = obj.next(price);
 */