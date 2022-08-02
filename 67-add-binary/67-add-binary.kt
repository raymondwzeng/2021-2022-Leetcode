class Solution {
    fun addBinary(a: String, b: String): String {
        //We have 2 strings - a and b, get the longer of the two -> Size comparison
            //This gives us the upper bound for our for loop
        //Iterate over the longer string
            //Add them given the cases
            /*  a b c    s c
                0 0 0 -> 0 0
                0 0 1 -> 1 0
                0 1 0 -> 1 0
                1 0 0 -> 1 0
                0 1 1 -> 0 1
                1 0 1 -> 0 1
                1 1 0 -> 0 1
                1 1 1 -> 1 1
            */
        val longerString : String = if(a.length > b.length) a.reversed() else b.reversed()
        val shorterString : String = if(a.length > b.length) b.reversed() else a.reversed()
        
        val returnStringBuilder = StringBuilder()
        var carry = '0'
        longerString.forEachIndexed { index, value ->
            val shorterChar = shorterString.getOrNull(index) ?: '0'
            when {
                (value == '0' && shorterChar == '0') -> {
                    if(carry == '1') {
                        returnStringBuilder.append('1')
                        carry = '0'
                    } else {
                        returnStringBuilder.append('0')
                    }
                }
                (value == '0' && shorterChar == '1') || (shorterChar == '0' && value == '1') -> {
                    if(carry == '1') {
                        returnStringBuilder.append('0')
                        carry = '1'
                    } else {
                        returnStringBuilder.append('1')
                    }
                }
                (value == '1' && shorterChar == '1') -> {
                    if(carry == '1') {
                        returnStringBuilder.append('1')
                    } else {
                        returnStringBuilder.append('0')
                        carry = '1'
                    }
                }
            }
        }
        if(carry == '1') returnStringBuilder.append('1')
        return returnStringBuilder.reverse().toString()
    }
}