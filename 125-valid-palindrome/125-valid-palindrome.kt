class Solution {
    fun isPalindrome(s: String): Boolean {
        //We should first start by formatting the text so that it does not contain any non-alphanumeric chars.
        //Should be just some linear scans.
        var str = s.toLowerCase().filter { it.isLetterOrDigit() }
        var left = 0
        var right = str.count() - 1
        while(left < right) { //Sort of linear (technically half) scan to check matching letters
            if(str.get(left) != str.get(right)) return false
            left++
            right--
        }
        return true
    }
}