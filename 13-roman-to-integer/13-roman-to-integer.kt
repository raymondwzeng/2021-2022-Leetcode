class Solution {
    fun getValueOfCharacter(c: Char): Int {
        return when(c) {
            'I' -> 1
            'V' -> 5
            'X' -> 10
            'L' -> 50
            'C' -> 100
            'D' -> 500
            'M' -> 1000
            else -> 0
        }
    }
    
    fun romanToInt(s: String): Int {
        //Take a sliding window of the string of size 2
        var runningTotal = 0
        var leftWindow = 0
        var rightWindow = 1
        while (leftWindow < s.length) {
            val rightCharacter = s.getOrNull(rightWindow)
            when {
                rightCharacter == null -> runningTotal += getValueOfCharacter(s.get(leftWindow))
                else -> {
                    val leftValue = getValueOfCharacter(s.get(leftWindow))
                    val rightValue = getValueOfCharacter(rightCharacter)
                    if(rightValue > leftValue) {
                        runningTotal += (rightValue - leftValue)
                        leftWindow++
                        rightWindow++
                    } else {
                        runningTotal += leftValue
                    }
                }
            }
            leftWindow++
            rightWindow++
        }
        return runningTotal
    }
}