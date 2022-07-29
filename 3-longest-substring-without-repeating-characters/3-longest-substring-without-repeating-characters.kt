class Solution {
    fun lengthOfLongestSubstring(s: String): Int {
        //Substring = contiguous
        /*
            Approach (sliding window with modification)
            
            -> Start with a sliding window of size 1 -> O(1)
            -> Create an empty set (O(1) is probably possible with ASCII dictionary, but it's a bit of work) -> O(1)
            -> Add the first element into the set within our sliding window -> O(1)
            -> Check the next element
                - If it exists within our set, we slide over.
                    "abcde"
                    - While the item still exists within our set, continue sliding until it is the first element in our window and then once more, removing item from our set afterwards
                    (Idea: We don't know where the first element is within our window, but we can slide until we get of it OR until we hit the end with our right side)
                - If it does not exist within our set, we expand our sliding window, and add to set.
                    
            We're doing a lot of additional work by repeating the amount of work to record the values that we have.
            Instead, maybe we can just remove elements at the head of our sliding window when we pass them?
            
            Maybe an O(n) solution, with O(n) space (because ASCII is weird)
        */
        //Rather than just having a 0/1 approach, consider storing frequency
        //When we encounter a duplicate character, we increment the value and slide.
        //At the head (left side), we lower the frequency
                    
        //The sliding window still stores a record of our longest string
        //Sliding it over and adding the freq just makes sure that we don't add another duplicate
        
        if(s.length == 0) return 0
        var maxLength = 1
        var left = 0
        var right = 0
        val characterArray = IntArray(95)
        characterArray[s[0] - ' ']++
        
        while(right < s.length - 1) {
            val nextCharacter = s[right + 1]
            if(characterArray[nextCharacter - ' '] == 0) {
                right++
                characterArray[nextCharacter - ' ']++
            } else {
                while(characterArray[nextCharacter - ' '] > 0) { //If the character is not on the left edge, we know that the current string length is impossible even sliding because of the overlap.
                    characterArray[s[left] - ' ']--
                    left++
                }
                characterArray[nextCharacter - ' ']++
                right++
            }
            if(right - left + 1 > maxLength) maxLength = (right - left) + 1
        }
        return maxLength 
    }
}