class Solution {
    fun characterReplacement(s: String, k: Int): Int {
        /*
            Do we care about the middle elements between the first and last indices of our string?
            
            Brute Force Solution:
                - Do a frequency map of each interval for all lengths between 2 and s.length
                - If the frequency map has a size <= k + 1, an element exists such that its frequency <= k, then we can convert all of those elemments into the other element if we want
                
            Sliding window -> Potentially bring us down to O(n) time where n = length of the string
            10^5 input size -> Linear time
            
            Start with a sliding window of size 1
            While the next element is either the same -> Increment freq map
                                        OR we have space to accomodate the element (map.size <= k + 1)
                                        -> Add the element to our freq map and update the values (including max length)
            Otherwise, slide the window forward, and remove back, add front until we can add the next element
            
            O(n) time because we don't scan any element more than once
            O(1) space because we are limited to uppercase English letters
        */
        if(s.length == 1) return 1
        val freqMap = mutableMapOf<Char, Int>(s[0] to 1)
        var left = 0
        var right = 0
        var dominantChar = s[0]
        /*
            size of frequency map => number of different characters
            need to keep track of the number of other letters we can still add -> "dominant character" -> Get the number of letters that we have of that character, and then the rest (because they are less) will be transformed to the dominant character
        */
        var maxLength = 1
        while(left <= right && right < s.length) {
            // println("Dominant character: $dominantChar")
            var charactersAdded = 1
            while(charactersAdded > 0) { //While we can grow the sliding window, either from dom char, or transforming others
                var charactersLeftToTransform = k - ((right - left + 1) - (freqMap.get(dominantChar) ?: 0))
                // println("Left: $left, Right: $right")
                charactersAdded = 0
                while(right < s.length - 1 && s[right + 1] == dominantChar) { //Grow the sliding window to as much as possible using the dominant char
                    right++
                    freqMap.put(dominantChar, (freqMap.get(dominantChar) ?: 0) + 1)
                }
                while(right < s.length - 1 && (charactersLeftToTransform > 0 || (freqMap.get(s[right + 1]) ?: 0) == (freqMap.get(dominantChar) ?: 0))) { //Growing the sliding window with nondominant characters
                        right++
                        freqMap.put(s[right], (freqMap.getOrPut(s[right], {0}) as Int) + 1)
                        charactersLeftToTransform--
                        if((freqMap.get(s[right]) ?: 0) > (freqMap.get(dominantChar) ?: 0)) { //Update dominant char if needed
                            dominantChar = s[right]
                            charactersLeftToTransform = k - ((right - left + 1) - (freqMap.get(dominantChar) ?: 0))
                        }
                        charactersAdded++
                    }
                }
            if(right - left + 1 > maxLength) maxLength = right - left + 1
            // println("Can't add more, shrink by 1")
            freqMap.put(s[left], (freqMap.get(s[left]) ?: 0) - 1)
            if((freqMap.get(s[left]) ?: 0) <= 0) freqMap.remove(s[left])
            if(left == right) {
                right++
                if(right < s.length) freqMap.put(s[right], freqMap.getOrPut(s[right], {0}) + 1)
            }
            freqMap.forEach { entry ->
                if(entry.value > (freqMap.get(dominantChar) ?: 0)) {
                    dominantChar = entry.key
                }
            }
            // println(freqMap)
            left++
            if(right == s.length - 1) return maxLength
        }
        return maxLength
    }
}