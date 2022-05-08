class Solution {
    fun isPalindrome(s: String): Boolean {
        var left = 0
        var right = s.length - 1
        val alphaNumeric = Regex("[a-zA-Z0-9]")
        while(left < right) {
            while(left < s.length && !s.substring(left..left).matches(alphaNumeric)) left++
            while(right > -1 && !s.substring(right..right).matches(alphaNumeric)) right--
            if(left < right && s.get(left).toLowerCase() != s.get(right).toLowerCase()) return false
            left++
            right--
        }
        return true
    }
}