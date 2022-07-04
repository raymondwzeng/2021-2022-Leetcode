class Solution {
    fun longestPalindrome(s: String): Int {
        //First solution that pops into my head: add all of them into an array or set
        //If even, add amount/2, if odd >= 3, add amount - 1.
        /*
            abccccdd
            a: 1 -> Add 1
            b: 1 -> Don't add
            c: 4 -> Add 4
            d: 2 -> Add 2
            
            Total: 4 + 2 + 1
        */
        if(s.isEmpty()) return 0
        var count = 0
        var hasAddedOne = false
        val characterMap = mutableMapOf<Char, Int>()
        s.forEach { character : Char ->
            if(characterMap.contains(character)) {
                characterMap.put(character, characterMap.get(character)!!.plus(1))
            } else {
                characterMap.put(character, 1)
            }
        }
        characterMap.forEach { character, frequency ->
            when {
                frequency % 2 == 0 -> count += frequency
                frequency % 2 == 1 && frequency >= 3 && !hasAddedOne -> {
                    count += frequency
                    hasAddedOne = true
                }
                frequency % 2 == 1 && frequency >= 3 && hasAddedOne -> count += frequency - 1
                frequency % 2 == 1 && frequency < 3 && !hasAddedOne -> {
                    count++
                    hasAddedOne = true
                }
            }
        }
        return count
    }
}