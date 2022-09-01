class Solution {
    fun reverseVowels(s: String): String {
        val stringArray = s.toCharArray()
        var left = 0
        var right = s.length - 1
        val vowelSet = setOf<Char>('a', 'A', 'e', 'E', 'i', 'I', 'o', 'O', 'u', 'U')
        while(left < right) {
            while(left < right && !vowelSet.contains(s[left])) left++
            while(left < right && !vowelSet.contains(s[right])) right--
            if(left < right) {
                val temp = stringArray[left]
                stringArray[left] = stringArray[right]
                stringArray[right] = temp
                left++
                right--
            }
        }
        return stringArray.joinToString("")
    }
}