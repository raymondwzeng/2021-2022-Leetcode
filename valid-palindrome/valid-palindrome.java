class Solution {
    public boolean isPalindrome(String s) {
        s = s.toLowerCase(); //Make lower case, possibly O(s)
        s = s.replaceAll("[^a-zA-Z0-9]", ""); //Also probably O(s)
        int left = 0;
        int right = s.length() - 1;
        while(left < right) { //Also O(s/2), and we don't care when they are equal because if left = right, the character is the same
            if(s.charAt(left) != s.charAt(right)) {
                return false;
            }
            left++;
            right--; //Don't forget to move
        }
        return true;
    }
}