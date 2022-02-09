class Solution {
    //Naive solution: O(l^2s) (where l is the length of the longer string, s the length of shorter)
    //You'd just iterate through all the characters in shorter string, and linear search through the other.
    
    //How can we do better?
    
    //Repeated work: Are we scanning sections that we don't care about?
    //Minor improvement: Only scan starting from the character index which you found the first one.
    //Roadblock: What if we have something like ababc & abc, we get "tricked" with the first input?
    //Actually, that doesn't matter. We can take first a, then any b, then c. In between doesn't matter, so the O(l^2s) would still be alright.
    
    /* Naive code
            int lastIndex = 0;
        for(int i = 0; i < shorterString.length(); i++) {
            for(int j = lastIndex; j < longerString.length(); j++) {
                if(longerString.charAt(j) == shorterString.charAt(i)) {
                    //We found the character
                    lastIndex = j+1;
                    lCS++;
                    break; //Break from the for loop, we don't need it anymore.
                }
            }
        } 
    */
    
    //Idea: What if we simply had a pointer towards the character we wanted?
    //We could eliminate the for loop then.
    
    //Now rather than scanning through the remaining subarray over and again, we simply move with the 2 pointers.
    //So at worst, we'd have to traverse all of the longer string, so O(l) with l being the longer string?
    //Problem 2: We could have the longest subsequence be a substring of the shorter string.
    public int longestCommonSubsequence(String text1, String text2) {
        String shorterString = "";
        String longerString = "";
        if(text1.length() < text2.length()) {
            shorterString = text1;
            longerString = text2;
        } else if (text2.length() <= text1.length()) {
            shorterString = text2;
            longerString = text1;
        }
        
        int[] previousSubsequence = new int[longerString.length()]; //Old subsequence to compare
        int[] maxSubsequence = new int[longerString.length()]; //New subsequence.
        
        for(int i = 0; i < shorterString.length(); i++) { //Iterate through the shorter string (keep track)
            for(int j = 0; j < longerString.length(); j++) { //Wait...isn't this also O(n^2)? Ah well. It's still a "dynamic programming" approach, though it doesn't actually save on runtime...is there a way that it could?
                if(longerString.charAt(j) == shorterString.charAt(i)) { //If the two characters are equal...
                    if(j == 0) { //If we're at the beginning
                    maxSubsequence[j] = 1;
                } else {
                    maxSubsequence[j] = (previousSubsequence[j-1] + 1 > 1) ? previousSubsequence[j-1] + 1 : 1;   
                }
                } else {
                    //Not equal characters, just grab the max of either what we had before (ignore the character), or previous in row.
                    if(j == 0) {
                        maxSubsequence[j] = previousSubsequence[j]; //Just grab the previous.
                    } else {
                        maxSubsequence[j] = (previousSubsequence[j] > maxSubsequence[j-1]) ? previousSubsequence[j] : maxSubsequence[j-1];
                    }
                }
                
            }
            previousSubsequence = maxSubsequence;
            maxSubsequence = new int[longerString.length()];
        }
        

        return previousSubsequence[longerString.length()-1];
    }
}