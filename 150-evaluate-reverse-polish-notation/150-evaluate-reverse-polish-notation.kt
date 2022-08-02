class Solution {
    fun evalRPN(tokens: Array<String>): Int {
        //Use a stack :)
        /*
            For each token in tokens
                If the value is an operand (number), add to the stack.
                    -> The order of the operations should be kept in mind
                If the value is an operator, then pop the last two elements from the stack, evaluate, and put the result back onto the stack.
                
            Runtime: O(n) where n is the number of elements within the array
            Space: O(n)
        */
        val stack = mutableListOf<Int>()
        
        tokens.forEach { string -> 
            when(string) {
                "+" -> {
                    val first = stack.first()
                    stack.removeAt(0)
                    val second = stack.first()
                    stack.removeAt(0)
                    stack.add(0, first + second)
                }
                "*" -> {
                    val first = stack.first()
                    stack.removeAt(0)
                    val second = stack.first()
                    stack.removeAt(0)
                    stack.add(0, first * second)
                }
                "-" -> {
                    val first = stack.first()
                    stack.removeAt(0)
                    val second = stack.first()
                    stack.removeAt(0)
                    stack.add(0, second - first)
                }
                "/" -> {
                    val first = stack.first()
                    stack.removeAt(0)
                    val second = stack.first()
                    stack.removeAt(0)
                    stack.add(0, second / first)
                }
                else -> stack.add(0, Integer.parseInt(string))
            }
        }
        
        return stack.first()
    }
}