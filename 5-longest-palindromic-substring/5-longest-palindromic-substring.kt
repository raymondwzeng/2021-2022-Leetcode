class Solution {
    fun longestPalindrome(s: String): String {
        //Sliding window approach
        /*
            Palindrome -> Odd length (middle is 1 character), or even length (middle is two characters)
            If (left hand side) at the beginning, then all elements must be the same character
        
        
            Psuedocode:
            Start with a sliding window of size 1
            If the left and right (exist) and are the same, or if the right hand side exists, expand -> SW of size 2 or 3
            While the left and right side of the sliding window match, expand.
                -> ["aaaabaaaa"] (duplicate work? maybe not)
            
            Otherwise, slide to the next character after the RHS (and shrink back to size 1)
            
            Keep track of the maximum
            
            Return max
            
            -> ["aaaaaaaa"] without optimizing for this case
            1 + 2 + 3 + ... + n -> O(n^2) where n is the length of the string
            O(1) space, we're not really collecting any additional data
            
            "babad"
        */
        if(s.length == 1) return s
        var longestWindow = ""
        var index = 0
        while(index < s.length) { //The only thing that we really need to check beforehand is the special even-core case
            var leftWindow = index
            var rightWindow = index
            while(rightWindow + 1 < s.length && s[rightWindow + 1] == s[index]) rightWindow++
            while(s.getOrNull(leftWindow - 1) == s.getOrNull(rightWindow + 1) && !(s.getOrNull(leftWindow - 1) == null && s.getOrNull(rightWindow + 1) == null)) { //Expand until one or BOTH are null.
                leftWindow--
                rightWindow++
            }
            if(rightWindow - leftWindow + 1 > longestWindow.length) longestWindow = s.substring(leftWindow.coerceIn(0, leftWindow), (rightWindow + 1).coerceIn((rightWindow + 1), s.length))
            index++
        }
        
        return longestWindow
    }
}