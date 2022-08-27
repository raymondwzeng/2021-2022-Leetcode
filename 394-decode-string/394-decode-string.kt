class Solution {
    
    /*
        Dry run
        
        3[a]2[bc]
        [a]2[bc] currRepeat = 3, next recurse on the next value
        ]2[bc] currRepeat = 0, apppend a into stringbuilder
        2[bc] currRepeat = 3, nothing in our stringbuilder, repeat 3 times -> aaa in stringbuilder
        [bc] currRepeat = 2, aaa in stringbuilder, recurse on inside
        c] currRepeat = 0, b in stringbuilder
        ] currRepeat = 0, bc in stringbuilder, next return bc
         currRepeat = 2, aaa in stringbuilder, repeat inside 2 times -> aaabcbc
         return
        
        3[a2[c]]
        [a2[c]] currRepeat = 3, recurse on substring
        a2[c]] currRepeat = 0
        2[c]] currRepeat = 0, stringbuilder = a
        [c]] currRepeat = 2, stringbuilder = a, recurse on substring
        c]] currRepeat = 0, stringbuilder empty
        ]] currRepeat = 0, stringbuilder c, return c
        ] currRepeat = 2, stringbuilder = a, repeat c 2 times to get acc, return acc
         currRepeat = 3, stringbuilder empty, repeat acc 3 times to get accaccacc
         return
         
         Problem: We're returning exactly to the same index that we called the function from, but realistically we want to continue from where we ended
    */
    
    data class ReturnPacket(val value: String, val index: Int)
    
    //Construct a string based off of the encoding from [begin], repeating [repeat] times, inclusive
    fun decodeString(s: String, begin: Int): ReturnPacket {
        val builder = StringBuilder()
        var currRepeat = 0 //Repeat times
        var currentIndex = begin
        while(currentIndex < s.length) {
            if(s[currentIndex] >= '0' && s[currentIndex] <= '9') { //Build up the number of times that we need to repeat
                currRepeat *= 10
                currRepeat += s[currentIndex] - '0'
            } else if(s[currentIndex] >= 'a' && s[currentIndex] <= 'z') { //Add the next value into our string
                builder.append(s[currentIndex])
            } else if(s[currentIndex] == '[') { //Evaluate the internal substring
                val packet = decodeString(s, currentIndex + 1)
                val substring = packet.value
                for(i in 0..currRepeat - 1) {
                    builder.append(substring)
                }
                currentIndex = packet.index
                currRepeat = 0
            } else if(s[currentIndex] == ']') { //Return immediately, we're done with our string
                return ReturnPacket(builder.toString(), currentIndex)
            }
            currentIndex++
        }
        return ReturnPacket(builder.toString(), currentIndex)
    }
    
    fun decodeString(s: String): String {
        //s <= 30
        //all ints within s are 1 <= i <= 300
        //Recursive solution, build up a string from its recursive subproblems
        return decodeString(s, 0).value
    }
}