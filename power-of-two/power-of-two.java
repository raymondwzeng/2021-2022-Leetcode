class Solution {
    public boolean isPowerOfTwo(int n) {
        //Check to see if a number is a power of two
        //64 -> 32 -> 16 -> 8 -> 4 -> 2 -> 1 Is ok
        //3 is not.
        //If it has more than 1 bit as a 1 in binary, I don't think it is a power?
        //Solution -> Check if the AND of the number in binary with the MSB is itself?
        //Question: How to get MSB? 
        //Solution: Integer.highestOneBit()
        //Edge case forgotten: 0
        //second edge case: Negatives! Because negatives aren't a thing.
        if (n == 0 || (n < 0 && n != -1)) return false;
        int highestBit = Integer.highestOneBit(n);
        int result = n & highestBit;
        if(result == n) return true;
        return false;
    }
}