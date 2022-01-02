class Solution {
    public int lengthOfLongestSubstring(String s) {
        //In this case, we can't modify the string
        //I think a sliding window approach might work
        if(s.length() == 0) return 0;
        int longestSubstringLength = 1;
        int left = 0;
        int right = 0;
        
        //Philosophy behind this approach
        //Assuming that we start with 1 character, we will simply deal with that.
        //Then, using the right side, we'll see if the new character fits.
        //The problem then occurs when we deal with a window size larger than 1.
        //However, this solution is fairly slow. If all of the values are unique, then we could devolve into an O(n^2) runtime
        //as we scan 1 + 2 + 3 + ... + (n-1) characters within the string.
        while(right < s.length() - 1) { //Before we reach the end
            int duplicateIndex = -1;
            for(int i = left; i <= right; i++) { //Scan through our existing window for duplicates
                if(s.charAt(right+1) == s.charAt(i)) {
                    duplicateIndex = i;
                    break;
                }
            }
            if(duplicateIndex != -1) {
            //If duplicate found, reset to right+1.
                int length = right - left + 1;
                if(length > longestSubstringLength) longestSubstringLength = length;
                left = duplicateIndex+1;
            }
            right++;
        }
        
        int finalLength = right - left + 1;
        if(finalLength > longestSubstringLength) longestSubstringLength = finalLength;
        
        return longestSubstringLength;
    }
}