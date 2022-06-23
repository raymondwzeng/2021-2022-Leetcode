class Solution {
    fun isAnagram(s: String, t: String): Boolean {
        //The straightforward approach is to create two maps of char -> int pairs based on frequency, then simply compare their values.
        val sMap = mutableMapOf<Char, Int>()
        val tMap = mutableMapOf<Char, Int>()
        s.forEach { character -> 
            sMap.put(character, sMap.getOrDefault(character, 0) + 1)
        }
        t.forEach { character -> 
            tMap.put(character, tMap.getOrDefault(character, 0) + 1)
        }
        
        //Then iterate through both of them to confirm that the frequencies are the exact same
        var isSame = true
        sMap.forEach { character, frequency ->
            if(tMap.getOrDefault(character, -1) != frequency) isSame = false //For some reason, can't return here labeled or not. Maybe just a LeetCode thing?
        }
        tMap.forEach { character, frequency ->
            if(sMap.getOrDefault(character, -1) != frequency) isSame = false
        }
        
        return isSame
    }
}