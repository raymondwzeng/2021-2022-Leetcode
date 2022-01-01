class Solution {
    public boolean isAnagram(String s, String t) {
        //Simple traversal of both strings, using arrays as frequency counter.
        //Since we're only dealing with lower case ASCII, we have technically constant space.
        //Since we need to traverse each string to get the proper frequency count, this uses O(n) time.
        if(s.length() != t.length()) return false; //One simple case.
        int[] sFrequency = new int[26];
        int[] tFrequency = new int[26];
        for(int i = 0; i < s.length(); i++) {
            sFrequency[s.charAt(i)-97] = sFrequency[s.charAt(i)-97] + 1;
            tFrequency[t.charAt(i)-97] = tFrequency[t.charAt(i)-97] + 1;
        }
        for(int j = 0; j < 26; j++) {
            if(sFrequency[j] != tFrequency[j]) return false;
        }
        return true;
    }
}