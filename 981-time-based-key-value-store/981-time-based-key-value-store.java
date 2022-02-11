class TimeMap {
    Map<String, TreeMap<Integer, String>> map;

    public TimeMap() {
        this.map = new HashMap<>();
    }
    
    public void set(String key, String value, int timestamp) {
        this.map.computeIfAbsent(key, a -> new TreeMap<Integer, String>()).put(timestamp, value);
    }
    
    public String get(String key, int timestamp) {
        if (!this.map.containsKey(key)) {
            return "";
        }
        
        TreeMap<Integer, String> keyMap = this.map.get(key);
        
        // get floor key <=, lower key <
        // get ceiling key >=, higher key >
        Integer floorKey = keyMap.floorKey(timestamp);
        
        if (floorKey == null) return "";
        return keyMap.get(floorKey);
    }
}

/**
 * Your TimeMap object will be instantiated and called as such:
 * TimeMap obj = new TimeMap();
 * obj.set(key,value,timestamp);
 * String param_2 = obj.get(key,timestamp);
 */