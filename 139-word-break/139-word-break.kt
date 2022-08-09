/* Own solution (too slow)
        //Dynamic programming?
        //Starting from left to whatever index we are at, can we make a word that is within our dictionary?
        //-> How do we check the substrings in a fast manner?
        //If starting a new string, find all candidates that start with the letter, then check each individual character as we iterate through it.
        val queueOfSubproblems = mutableListOf<String>(s) //Int -> Indices within the dictionary to check against
        //At each letter, we can choose to either continue with an existing set of words, or start a new word.
        
        val substringMap = mutableMapOf<String, Boolean>()
        
        //Is the subproblem just the string with 1 less char at the beginning?
        //If the word matches, then the subproblem is the string minus the word?
        //leetcode, break into a subproblem where s = leet or s = code?
        while(queueOfSubproblems.isNotEmpty()) {
            val front = queueOfSubproblems.first()
            // println("String: $front")
            var hasSubstring = false
            wordDict.forEach { word ->
                val regex = Regex(word)
                val find = regex.find(front, 0)
                if(find != null && find.range.start == 0) { //If we had a match
                    val substring = regex.replaceFirst(front, "")
                    if(substring == "") {
                        return true
                    }
                    if(substringMap.get(substring) == null) {
                        queueOfSubproblems.add(substring)
                        // println("Produced substring: $substring")
                        hasSubstring = true
                    }
                }
            }
            if(hasSubstring == false) substringMap.put(front, false) //This substring is bad, and shouldn't be pursued
            queueOfSubproblems.removeAt(0)
        }
        //Runtime: Up to n items within each iteration (where n = # of items in wordDict), each iteration runs through n items -> O(n^2)
        //Space: O(n) space
        
        
        //Left to right?
        /*
            andog (-cats)
                -> og (-and)* -> Some overlapping subproblems, but how do we get to this point?
                -> an (-dog)
            sandog (-cat)
                -> og (-og)*
                -> san (-dog)
                -> sog (-and)
            catsan (-dog)
            catog (-sand)
            catsog (-and)
        */
        
        return false
*/

class Solution {
    private lateinit var dp: Array<Boolean?>
    
    fun wordBreak(s: String, wordDict: List<String>): Boolean {
        dp = Array<Boolean?>(s.length) { null }
        return wordBreakAtIndex(0, s, wordDict)
    }
    
    fun wordBreakAtIndex(index: Int, s: String, wordDict: List<String>): Boolean {
        if(index == s.length) return true //A bit weird to compare index to size when it realistically shouldn't ever hit this, but...
        if(dp[index] != null) return dp[index]!!
        for(i in index + 1..s.length) {
            if(wordDict.contains(s.substring(index, i)) && wordBreakAtIndex(i, s, wordDict) == true) { //Recurse on the substring if we have a match at the lefthand side starting at index, returning true if our substring is also valid
                dp[index] = true
                return true
            }
        }
        dp[index] = false //Return false if our substring is not valid
        return false
    }
}