//Simple linear solution to just check all of them.
//Question: Is there a cleaner solution?

class Solution {
    public boolean isValid(String s) {
        Stack<Character> openStack = new Stack<>();
        for(int i = 0; i < s.length(); i++) {
            if(s.charAt(i) == '(' || s.charAt(i) == '{' || s.charAt(i) == '[') {
                openStack.push(s.charAt(i));
            } else if (openStack.isEmpty()) {
                return false;  
            } else if (s.charAt(i) == ')') {
                if(openStack.peek() != '(') return false;
                openStack.pop();
            } else if (s.charAt(i) == '}') {
                if(openStack.peek() != '{') return false;
                openStack.pop();
            } else if (s.charAt(i) == ']') {
                if(openStack.peek() != '[') return false;
                openStack.pop();
            }
        }
        return openStack.isEmpty();
    }
}