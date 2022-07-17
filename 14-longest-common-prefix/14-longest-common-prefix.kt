class Solution {
    fun longestCommonPrefix(strs: Array<String>): String {
        /*
            Brute force:
                For each character in each string of the array...
                Check the characters of the other strings
                And see if they match.
                
                ABCDE
                FGHI
                JKLM
                
                Potential two pointer + sliding window approach
                
                Beginning with the longest string (O(nlogn) sort?) and its starting character
                Begin with a sliding window of size 1.
                If the character matches, increase the window to size 2, and so on.
                If the character does not match, reset window size to 1 and slide the window forward.
                Continue until we've gone through all elements in the longest string.
                
                Bottlenecks:
                    - We need to get the longest (or smallest) string -> The longest string could have a common prefix that matches a smaller string in the array.
                    - We need to check through the entirety of the other strings, each iteration.
                        -> Spend some space to mark out all of the potential areas of focus.
                        
                Runtime:
                    - The length of the smallest string (s) * (the length of the longest string * # of strings)
                    
            Better(?) Solution:
                - Grab the shortest string (O(nlogn) sort)
                - For each other string, iterate through them and try to expand the sliding window to the sortest string's length.
                - Keep track of the minimum, then return a substring of the smallest string at the end.
                
                O(nlogn) [sort] + O(string length * # of strings in the array)
                
                Flow
                Aflatlow
                
            Correct Solution:
                - n pointers solution -> Check the value of all of the other elements at that index
                - O(nlogn) [sort] + O(s * n) [shortest string * # of strings in the list - 1]
        */
        strs.sortBy { it.length }
        if(strs.size == 1) return strs[0]
        val stringBuilder = StringBuilder()
        strs[0].forEachIndexed {index, char -> 
            for(i in 1..strs.size - 1) {
                if(char != strs[i][index]) return stringBuilder.toString()
            }
            stringBuilder.append(char)
        }
        return stringBuilder.toString()
    }
}