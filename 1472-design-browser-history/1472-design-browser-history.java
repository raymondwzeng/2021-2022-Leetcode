class BrowserHistory {
    
    // List<String> pages = new LinkedList<String>(); //This will be our "chain" of pages. It makes sense to use an LL here due to its ability to move forward and backwards.
    // int navigator;
    Stack<String> backHistory = new Stack<>();
    Stack<String> forwardHistory = new Stack<>();
    
    String currURL;

    public BrowserHistory(String homepage) {
        // if(pages.isEmpty()) navigator = 0; //Set navigator if the pages is empty.
        // pages.add(homepage);
        this.currURL = homepage; //We need to preserve at least the homepage, in order to move ahead on other examples.
    }
    
    public void visit(String url) {
        //Problem - we need to eliminate ALL of the forward history - or at least disconnect it due to Java GC?
        //If memory ends up being a problem, then remove that list in an O(n) operation.
        //Actually, what if we ended up using two lists instead - one for forward history, one for back history?
        //What order do we want? Well, if we move backwards, then we want to move to the most recent page. So LIFO?
        //But in the case of the forward, we want to move forward...also LIFO?
        //So 2 stacks instead?
        backHistory.push(currURL); //Save our current URL in the backHistory.
        forwardHistory = new Stack<>(); //Clear the forward stack as per instructions
        this.currURL = url;
    }
    
    public String back(int steps) {
        for(int i = 0; i < steps; i++) { //Go steps backwards, if empty stop.
            if(backHistory.isEmpty()) {
                break; 
            }
            forwardHistory.push(currURL); //Interesting blunder: I should PUSH my current URL into the appropriate stack, so that I can go back to it appropriately later.
            currURL = backHistory.pop();
        }
        return currURL;
    }
    
    public String forward(int steps) {
        System.out.println(currURL);
        for(int i = 0; i < steps; i++) { //The same thing, but forwards.
            if(forwardHistory.isEmpty()) {
                break; 
            }
            backHistory.push(currURL);
            currURL = forwardHistory.pop();
        }
        return currURL;
    }
}

/**
 * Your BrowserHistory object will be instantiated and called as such:
 * BrowserHistory obj = new BrowserHistory(homepage);
 * obj.visit(url);
 * String param_2 = obj.back(steps);
 * String param_3 = obj.forward(steps);
 */