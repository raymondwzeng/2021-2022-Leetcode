class Solution {
    fun isValid(s: String): Boolean {
        var stack = ArrayDeque<Char>()
        for(element in s) {
            when(element as Char) {
                '(', '[', '{' -> stack.addLast(element) //Found out about this from another solution - you can stack by commas!
                ')' -> if (stack.isEmpty() || stack.removeLast() != '(') return false
                '}' -> if (stack.isEmpty() || stack.removeLast() != '{') return false
                ']' -> if (stack.isEmpty() || stack.removeLast() != '[') return false
            }
        }
        return stack.isEmpty()
    }
}