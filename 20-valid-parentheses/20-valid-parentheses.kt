class Solution {
    fun isValid(s: String): Boolean {
        var stack = ArrayDeque<Char>()
        for(element in s) {
            when(element as Char) {
                '(' -> stack.addLast(element)
                '[' -> stack.addLast(element)
                '{' -> stack.addLast(element)
                ')' -> if (stack.isEmpty() || stack.removeLast() != '(') return false
                '}' -> if (stack.isEmpty() || stack.removeLast() != '{') return false
                ']' -> if (stack.isEmpty() || stack.removeLast() != '[') return false
            }
        }
        return stack.isEmpty()
    }
}