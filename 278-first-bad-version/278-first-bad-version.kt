/* The isBadVersion API is defined in the parent class VersionControl.
      fun isBadVersion(version: Int) : Boolean {} */

class Solution: VersionControl() {
    override fun firstBadVersion(n: Int) : Int {
        //The obvious strategy is to check all versions until one is bad.
        //Time complexity: O(n) where n is the latest version.
        
        //However, an improvement can be made based on the following assumption:
        //Suppose that we have a latest version n that is bad.
        //Check version n/2. If n/2 is bad, then we know that either it OR a previous version is bad.
        //If n/2 is NOT bad, then we know that a version above n/2 is bad.
        //Time complexity: O(logn) because we are halving our search area each time.
        
        if(isBadVersion(1) || n == 1) return 1 //Only 1 version or initial bad, because nothing can be before it in that case
        
        var latestGoodVersion = 1 //Check 1, if is 1, return.
        var earliestBadVersion = n
        
        while(earliestBadVersion - latestGoodVersion != 1) {
            //NOTE: Convert to long because we could potentially have overflow when dealing with 2^31-1 + 2^31-1/2
            var middleVersion = ((latestGoodVersion.toLong() + earliestBadVersion.toLong()) / 2).toInt()
            if(isBadVersion(middleVersion)) {
                earliestBadVersion = middleVersion
            } else {
                latestGoodVersion = middleVersion
            }
        }
        return earliestBadVersion
        /*
        Runthrough of n = 5
        
        lGV = 1
        eGV = 5
        middle = 5 + 1 / 2 = 3 -> Good
        lGV = 3
        eGV = 5
        middle = 3 + 5 / 2 = 4 -> Bad
        lGV = 3
        eGV = 4
        Since 4 - 3 == 1, then we know that eBV is the bad one
        
        lGV = 1
        eGV = 2
        //Auto done
        */
	}
}