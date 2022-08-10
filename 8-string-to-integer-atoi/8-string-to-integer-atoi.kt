class Solution {
    fun myAtoi(s: String): Int {
        /*
            Perform a sort of linear scan on the string, and do the proper modifications
            
            Main issue -> Dealing with zeroes (determining between leading zeroes and zeroes that have context)
            
            0032 -> 32
            1032 -> 1032
            
            Read zeroes at the beginning until you hit either a decimal or a nonzero number
        */
        if(s.length == 0) return 0
        var trimmedString = s.trim()
        val numericalRegex = Regex("[0-9]")
        var sum = 0
        var sign = 1
        var leadingZeroesGone = false
        trimmedString.forEachIndexed { index, char ->
            if(numericalRegex.matches(trimmedString.substring(index, index + 1))) {
                if(char != '0' || leadingZeroesGone) {
                    if(!leadingZeroesGone) leadingZeroesGone = true
                    if(sign == -1 && ((sum > Integer.MAX_VALUE / 10) || (sum == Integer.MAX_VALUE / 10 && char - '0' >= 8))) {
                        return Integer.MIN_VALUE
                    } else if (sign == 1 && ((sum > Integer.MAX_VALUE / 10) || (sum == Integer.MAX_VALUE / 10 && char - '0' >= 7))) {
                        return Integer.MAX_VALUE
                    } else {
                        sum *= 10
                        sum += char - '0'
                    }
                    
                }
            } else if (index == 0 && (char == '+' || char == '-')) {
                if(char == '-') sign = -1
            } else {
                return sum * sign
            }
        }
        return sum * sign
    }
}