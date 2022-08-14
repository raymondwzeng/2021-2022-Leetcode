class Solution {
    fun getCharacterListFromDigit(digit: Char): List<Char> {
        return when(digit) {
            '2' -> listOf('a', 'b', 'c')
            '3' -> listOf('d', 'e', 'f')
            '4' -> listOf('g', 'h', 'i')
            '5' -> listOf('j', 'k', 'l')
            '6' -> listOf('m', 'n', 'o')
            '7' -> listOf('p', 'q', 'r', 's')
            '8' -> listOf('t', 'u', 'v')
            '9' -> listOf('w', 'x', 'y', 'z')
            else -> emptyList()
        }
    }   
    
    fun solve(partialCandidate: String, sourceList: List<List<Char>>, listIndex: Int, charIndex: Int, solutionSet: MutableList<String>, targetLength: Int) {
        // println("Current candidate: $partialCandidate")
        // println("New character: ${sourceList[listIndex][charIndex]}")
        val newCandidate = partialCandidate + sourceList[listIndex][charIndex]
        if(newCandidate.length == targetLength) {
            solutionSet.add(newCandidate)
            // println("New finished candidate: $newCandidate")
        } else {
            if(listIndex + 1 < sourceList.size) solve(newCandidate, sourceList, listIndex + 1, 0, solutionSet, targetLength)
        }
        if(charIndex + 1 < sourceList[listIndex].size) solve(newCandidate.dropLast(1), sourceList, listIndex, charIndex + 1, solutionSet, targetLength)
    }
    
    fun letterCombinations(digits: String): List<String> {
        //Subsets problem, but with digits instead of pure numbers
        /*
            Create a helper function that essentially maps Char (as numbers) -> List<String>
            Create a secondary function that returns the permutations of n lists.
            
            # of digits is between 0 and 4, which means this algorithm is probably incredibly spicy (O(2^n) if not more)
            
            Edge case: If digits.length == 0 return emptyList()
            
            In subsets, we only need to iterate over a single list.
            Now, we have to iterate over multiple lists 
            
            *No guarantee that the digits that we're given are unique
                -> But that's fine because we can just have those 4 lists
                
            Assuming that we have n digits
            Then we would have 3n or 4n total size in terms of lists alone -> O(n) space
            For each character, we make up to n choices in terms of what to take -> O(n^n) time
            
            Note: Study big O with regards to subsets
            
            
            Example: 23 -> [a, b, c] [d, e, f]
            
            [a, d][a, e][a, f][b, d][b, e]... -> Reference to the individual list -> Make sure we don't add from our current list. (fEI)
            How do we make sure that this holds for all candidates?
            
            234 -> [a, b, c][d, e, f][g, h, i]
            [a, d, g][] -> We need to make sure we don't get any duplicates for the other lists either
            [a] [b] [c]
            [a, d][a, e][a, f][b, d][b, e][b, f][c, d][c, e][c, f] -> Go through each one in order
            
            23 -> [a, b, c] [d, e, f], starting with []
            ["a", "b", "c"]
            ["ad", "bd", "cd", "ae", "be", "ce", "af", "bf", "cf"]
        */
        if(digits.length == 0) return emptyList()
        val listOfSources = mutableListOf<List<Char>>()
        val listOfCandidates = mutableListOf<String>()
        
        digits.forEach { digit ->
            listOfSources.add(getCharacterListFromDigit(digit))
        }
        
        solve("", listOfSources, 0, 0, listOfCandidates, digits.length)
        return listOfCandidates
    }
}