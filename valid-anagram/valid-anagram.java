class Solution {
    public boolean isAnagram(String s, String t) {
        //Simple solution: Frequency map of both strings!
        //OH we can use arrays since it's just lowercase alphas
        if(s.length() != t.length()) return false; //If they're not the same length, don't even bother.
        int[] freqS = new int[26];
        int[] freqT = new int[26];
        for(int i = 0; i < s.length(); i++) {
            freqS[(int)s.charAt(i)-97] = freqS[(int)s.charAt(i)-97] + 1;
            freqT[(int)t.charAt(i)-97] = freqT[(int)t.charAt(i)-97] + 1;
        }
        
        for(int j = 0; j < 26; j++) {
            if(freqT[j] != freqS[j]) return false;
        }
        return true;
    }
}