class Solution {
    fun isPalindrome(s: String): Boolean {
        val str = s.toLowerCase() //Deprecated in 1.5, but LeetCode is using 1.3
        var left = 0
        var right = str.length - 1
        val alphaNumeric = Regex("[a-z0-9]")
        while(left < right) {
            while(left < str.length && !str.substring(left..left).matches(alphaNumeric)) left++
            while(right > -1 && !str.substring(right..right).matches(alphaNumeric)) right--
            if(left < right && str.get(left) != str.get(right)) return false
            left++
            right--
        }
        return true
    }
}