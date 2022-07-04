class Solution {
    fun canConstruct(ransomNote: String, magazine: String): Boolean {
        //In this case, we could either use a frequency map of characters, or an array of 26 size.
        //We only need to compare in one direction - from note to magazine - vs both in the case of valid anagram. Also of course, consider that the magazine only needs to be a superset of the note, not an exact match
        val noteArray = IntArray(26)
        val magazineArray = IntArray(26)
        
        ransomNote.forEach { character : Char ->
            val index = character - 'a'
            noteArray[index] = noteArray[index] + 1
        }
        
        magazine.forEach { character : Char -> 
            val index = character - 'a'
            magazineArray[index] = magazineArray[index] + 1
        }
        
        noteArray.forEachIndexed { index, freq ->
            if(magazineArray[index] < freq) return false
        }
        return true
    }
}