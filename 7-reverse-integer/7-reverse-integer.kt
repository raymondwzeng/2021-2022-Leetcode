class Solution {
    fun reverse(x: Int): Int {
        /*
            To get the last digit in an integer, we can mod the number with 10
            
            123 % 10 = 3
            
            Then we just need to really figure out how to multiply the digit back in
            
            Create an integer list, append the digits one by one, then rebuild the structure, keeping the sign
            
            O(logX) time, O(logX) space, where X is the original integer
        */
        if((x >= 0 && x < 10) || (x > -10 && x < 0)) return x
        val digitsList = LinkedList<Int>()
        val sign = if(x > 0) 1 else -1
        if(x == Integer.MIN_VALUE) return 0 //Corner case - we can't process MIN_VALUE normally at all
        var currentNumber = Math.abs(x)
        while(currentNumber > 0) { //Break down the integer into digits
            digitsList.add(currentNumber % 10)
            currentNumber /= 10
        }
        digitsList.forEachIndexed { index, num ->
            if((currentNumber * 10)/10 != currentNumber) return@reverse 0 //Overflow for +, - numbers
            currentNumber = (currentNumber * 10) + num
        }
        return currentNumber * sign
    }
}