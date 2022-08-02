class Solution {
    fun isPalindrome(x: Int): Boolean {
        //Straightforward -> Check if is equal after toString().reversed()
        /*
            Sort of mathematical/algo approach
                -> Negative numbers will never be a palindrome
            
            Produce a stack (a list) of values for each digit, then check if the list/stack is a palindrome
            (Add last value to the list by mod...10, then divide by that mod)
            
            Runtime is capped by the number of digits in our number because we need to iterate over them twice
            Linear runtime, linear space
        */
        if(x < 0) return false //Corner cases
        if(x < 10) return true
        val digitsList = mutableListOf<Int>()
        var number = x
        while(number > 0) {
            digitsList.add(number % 10)
            number /= 10
        }
        var left = 0
        var right = digitsList.size - 1
        while(left < right) {
            if(digitsList[left] != digitsList[right]) return false
            left++
            right--
        }
        return true
    }
}