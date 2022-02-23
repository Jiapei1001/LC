class Logger {
    Map<String, Integer> timeMap;

    public Logger() {
        timeMap = new HashMap<>();
    }
    
    public boolean shouldPrintMessage(int timestamp, String message) {
        if (!timeMap.containsKey(message) || timeMap.get(message) + 10 <= timestamp) {
            timeMap.put(message, timestamp);
            return true;
        }
        
        return false;
    }
}

/**
 * Your Logger object will be instantiated and called as such:
 * Logger obj = new Logger();
 * boolean param_1 = obj.shouldPrintMessage(timestamp,message);
 */