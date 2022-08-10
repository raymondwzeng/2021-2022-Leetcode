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
        var sum : Long = 0
        var sign = 1
        var leadingZeroesGone = false
        trimmedString.forEachIndexed { index, char ->
            if(numericalRegex.matches(trimmedString.substring(index, index + 1))) {
                if(char != '0' || leadingZeroesGone) {
                    if(!leadingZeroesGone) leadingZeroesGone = true
                        sum *= 10
                        sum += char - '0'
                        if(sign == 1 && sum > Integer.MAX_VALUE) return Integer.MAX_VALUE
                        if(sign == -1 && sum > Integer.MAX_VALUE.toLong() + 1) return Integer.MIN_VALUE
                    }
            } else if (index == 0 && (char == '+' || char == '-')) {
                if(char == '-') sign = -1
            } else {
                return (sum * sign).coerceIn(Integer.MIN_VALUE.toLong(), Integer.MAX_VALUE.toLong()).toInt()
            }
        }
        return (sum * sign).coerceIn(Integer.MIN_VALUE.toLong(), Integer.MAX_VALUE.toLong()).toInt()
    }
}