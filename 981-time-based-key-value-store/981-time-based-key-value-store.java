// TreeMap + floorKey
/*
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
*/

class TimeMap {
    Map<String, List<Data>> map;

    public TimeMap() {
        this.map = new HashMap<>();
    }
    
    public void set(String key, String value, int timestamp) {
        this.map.computeIfAbsent(key, a -> new ArrayList<Data>()).add(new Data(timestamp, value));
    }
    
    public String get(String key, int timestamp) {
        if (!this.map.containsKey(key)) {
            return "";
        }
        
        List<Data> list = this.map.get(key);
        return this.binarySearchLessThan(list, timestamp);
    }
    
    private String binarySearchLessThan(List<Data> list, int target) {
        int l = 0, r = list.size() - 1;
        
        while (l + 1 < r) {
            int mid = l + (r - l) / 2;
            
            if (list.get(mid).time <= target) {
                l = mid;
            } else {
                r = mid;
            }
        }
        
        if (list.get(r).time <= target) return list.get(r).val;
        if (list.get(l).time <= target) return list.get(l).val;
        
        return "";
    }
}

class Data {
    int time;
    String val;
    
    public Data (int time, String val) {
        this.time = time;
        this.val = val;
    }
}


/**
 * Your TimeMap object will be instantiated and called as such:
 * TimeMap obj = new TimeMap();
 * obj.set(key,value,timestamp);
 * String param_2 = obj.get(key,timestamp);
 */