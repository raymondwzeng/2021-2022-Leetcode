class Solution {
    fun numDecodings(s: String): Int {
        /*
            Input: String of digits
            Output: Number of ways that the string can be decoded
            
            Contingent on constraints:
                - The encoding can not start with 0
                - The encoding as a number can not be larger than 26
                
            Length of string [0, 100] --> O(s^2) algorithm
            
            Dynamic programming (word break?)
            
            The number of ways that our current problem can be decoded:
                1 (assuming that our current decoding is valid) + the number of ways that we can decode the rest of the string
                
            2 ways to break down the problem
                - Assuming we have 0 items in our decoding, we can take the next number and the number of ways that the rest can be decoded (n - 1)
                - Assuming that we have 1 number in our decoding, we can take the next number IFF our current number is 1 or 2, and wrt 2, the next number must be 6 (we may not need to care about this if we are backtracking)
                
            11106
            
            [1, 1, 1, 0, 6] -> Splitting up characters into their own cell in an array
            [1, 2, 3, 2, 2] -> DP Array, number of ways that we can decode up to this character
            We can either decode the number on its own (provided the value != 0)
            We can also decode it as a pair based on the previous number
            
            The sum of
                # ways we can break down the string in n - 1 (only if valid) [or 1 if n - 1 < 0] + # ways we can break down the string in n - 2 (only if valid) [or 1 if n - 2 == -1]
                
            Corner case: Leads with 0, 0 ways
            
            11
            AA
            K
            
            111
            AAA
            AK
            KA
            
            1110
            AAJ
            KJ
            
            11106
            
            
            1111
            AAAA
            AAJ
            AJA
            JAA
            JJ
            
            O(N) time, O(N) space where N is the length of the string
            
            226
            123
        */
        if(s[0] == '0') return 0
        if(s.length == 1) return 1
        val dpArray = IntArray(s.length){0}
        s.forEachIndexed { index, char ->
            val decodeAsSelf = if(char != '0') {
                if(index - 1 < 0) 1 else dpArray[index - 1]
            } else 0
            val decodeAsPair = if(index - 1 >= 0 && s[index - 1] != '0') { //As long as we have a previous character to try, attempt to do so
                val pair = Integer.parseInt("${s[index - 1]}${s[index]}")
                when {
                    pair > 26 -> 0
                    index - 2 < 0 -> 1
                    else -> dpArray[index - 2]
                }
            } else 0
            dpArray[index] = decodeAsSelf + decodeAsPair
            if(dpArray[index] == 0) return 0 //Small optimization if we see a pair of 0s
        }
        return dpArray[s.length - 1]
    }
}