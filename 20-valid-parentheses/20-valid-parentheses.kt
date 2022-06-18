class Solution {
    fun isValid(s: String): Boolean {
        //Approach: Use a stack, push open and pop closed paren
        val stack = mutableListOf<Char>()
        s.forEach{ char ->
            when (char) {
                '(' , '{' , '[' -> stack.add(0, char) //Add to front
                ')' -> if(stack.elementAtOrNull(0) != '(') return false else stack.removeAt(0)
                ']' -> if(stack.elementAtOrNull(0) != '[') return false else stack.removeAt(0)
                '}' -> if(stack.elementAtOrNull(0) != '{') return false else stack.removeAt(0)
            }
        }
        return stack.isEmpty()
    }
}