class Solution {
    public String getHint(String secret, String guess) {
//         1807, 7810
//         x := # of same chars
//         y := chars inside secret, but in wrong location
//         7
//         1
//         0
        
//         x + y = # of chars inside secret
//         secret: 0:1, 1:1, 7:1, 8:1
//         guess:  0:1, 1:1, 7:1, 8:1
//         1 + 3 = 4
        
//         secret: 1:2, 2:1, 3:1
//         guess:  1:3, 0:1
        
//         1 + 1 = 2
        if (secret.length() != guess.length()) return null;
        
        Map<Character, Integer> sMap = new HashMap<>();
        for (char c : secret.toCharArray()) {
            sMap.put(c, sMap.getOrDefault(c, 0) + 1);
        }
        
        Map<Character, Integer> gMap = new HashMap<>();
        for (char c : guess.toCharArray()) {
            gMap.put(c, gMap.getOrDefault(c, 0) + 1);
        }
        
        int total = 0;
        for (char c = '0'; c <= '9'; c++) {
            total += Math.min(sMap.getOrDefault(c, 0), gMap.getOrDefault(c, 0));
        }
        
        int same = 0;
        for (int i = 0; i < secret.length(); i++) {
            if (secret.charAt(i) == guess.charAt(i)) {
                same++;
            }
        }
        
        int diff = total - same;
        
        return same + "A" + diff + "B";
    }
}