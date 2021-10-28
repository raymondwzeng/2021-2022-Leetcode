class Solution {
    public boolean isValid(String s) {
        Stack<Character> stack = new Stack<Character>();
        for(int i = 0; i < s.length(); i++) {
            if(s.charAt(i) == ')') {
                if(stack.empty() || stack.peek() != '(') return false;
                stack.pop();
            } else if (s.charAt(i) == ']') {
                if(stack.empty() || stack.peek() != '[') return false;
                stack.pop();
            } else if (s.charAt(i) == '}') {
                if(stack.empty() || stack.peek() != '{') return false;
                stack.pop();
            } else {
                stack.push(s.charAt(i));
            }
        }
        if(!stack.empty()) {
            return false;
        }
        return true;
    }
}