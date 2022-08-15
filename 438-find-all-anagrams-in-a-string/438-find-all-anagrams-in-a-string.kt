class Solution {
    fun findAnagrams(s: String, p: String): List<Int> {
        /*
            If p.length > s.length return emptyList -> If this is true, then there can not be an anagram of p within s
            Similarly, if p.length == s.length, we only need to check if the word starting at 0 is an anagram of p
            
            Sliding window approach
                Create a sliding window of size p.length, and add all elements into a map (ofc, we'll also mapify p)
                Check to see if the maps are equivalent
                Slide the window forward, removing the element at the back (or decreasing the freq by 1) and add the element in the front
                Continue until the sliding window can not move forward more
            
            Time complexity: O(p) + O(s/p * p) -> O(s)
            Space complexity: O(p) + O(p) -> O(p)
        */
        if(p.length > s.length) return emptyList()
        
        val pFrequencyMap = mutableMapOf<Char, Int>()
        val sFrequencyMap = mutableMapOf<Char, Int>()
        val returnList = mutableListOf<Int>()
        
        p.forEach { char ->
            pFrequencyMap.put(char, pFrequencyMap.getOrPut(char, {0}) + 1)
        }
        
        var left = 0
        var right = p.length - 1
        
        for(index in left..right) { //Start by populating the initial map of sliding window
            sFrequencyMap.put(s[index], sFrequencyMap.getOrPut(s[index], {0}) + 1)
        }
        
        while(right < s.length) {
            if(pFrequencyMap.equals(sFrequencyMap)) { //If the map frequencies are exactly the same, then we can add to list
                returnList.add(left)
            }
            if(right + 1 < s.length) sFrequencyMap.put(s[right + 1], sFrequencyMap.getOrPut(s[right + 1], {0}) + 1) //Insert or increment incoming right side
            if(sFrequencyMap.get(s[left]) == 1) { //Remove or decrement the outgoing left side
                sFrequencyMap.remove(s[left])
            } else {
                if(sFrequencyMap.get(s[left]) != null) {
                     val previousValue = sFrequencyMap.get(s[left]) as Int
                     sFrequencyMap.put(s[left], previousValue - 1)
                }
            }
            right++ //Move the sliding window forward
            left++
        }
        
        return returnList
    }
}