class Solution {
    public boolean isPalindrome(String s) {
        //One solution: Copy the clean string, and compare with pointers.
        //O(s) time, but also O(s) space.
        s = s.toLowerCase();
        s = s.replaceAll("[^a-zA-Z0-9]", ""); //Use regex (huh. weird.) to remove whitespace.
        StringBuffer buffer = new StringBuffer(s);
        String cpy = buffer.reverse().toString();
        for(int i = 0; i < s.length(); i++) {
            if(s.charAt(i) != cpy.charAt(i)) return false;
        }
        return true;
    }
}