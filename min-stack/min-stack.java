class MinStack {
    int min = Integer.MAX_VALUE;
    Stack<Integer> minStack = new Stack<Integer>();

    public MinStack() {}
    
    public void push(int val) {
        if(val <= min) {
            //If the value is less than or equal to the min, we wanna add that to the stack
            //Basically, the logic goes that if we have a new min, then that min should go 2nd.
            //This actually results in a double-count, which we address when popping.
            minStack.add(min);
            min = val;
        }
        minStack.add(val);
    }
    
    public void pop() {
        //To address the previous issue, we check if our min is equal to the value we just removed.
        //In that case, we need to remove the next one as well.
        if (minStack.pop() == min)
        {
            min = minStack.pop();
        }
    }
    
    public int top() {
        return minStack.peek();
    }
    
    public int getMin() {
        return min;
    }
}

/**
 * Your MinStack object will be instantiated and called as such:
 * MinStack obj = new MinStack();
 * obj.push(val);
 * obj.pop();
 * int param_3 = obj.top();
 * int param_4 = obj.getMin();
 */