class Solution(w: IntArray) {
    //We are given an array of values
    //Could cache the sum of the values
    
    /*
        Roll an initial value
        Go down the list of items/weights, and if the value is less than that amount, we pick it, otherwise, we subtract?
        
        [1, 3] sum = 4
        Roll a number between 1 and 4
        Go from the max weight to the min weight
        If number > currentWeight, then subtract the amount and continue
        
        
        [1, 2, 3, 4] sum = 10
        Roll a number between 1 and 10
        [1, 2, 3, 4, 5, 6, 7, 8, 9, 10]
        [4, 4, 4, 4, 3, 3, 3, 2, 2, 1] -> Works
        
        [3, 14, 1, 7]
        [1, 3, 7, 14] sum = 25
        1-14 would hit 14
        15-21 would hit 7
        22-24 would hit 3
        25 would hit 1
    */
    data class Item(val weight: Int, val index: Int)
    
    val rng = Random()
    val itemList = mutableListOf<Item>()
    var sum = 0
    init {
        w.forEachIndexed { index, num -> //O(n)
            itemList.add(Item(num, index))
            sum += num
        }
        itemList.sortedBy{ item -> item.weight } //O(nlogn)
    }
    
    fun pickIndex(): Int {
        var currentIndex = itemList.size - 1
        var currentValue = rng.nextInt(sum) + 1
        while(currentValue > itemList[currentIndex].weight && currentIndex > 0) {
            currentValue = currentValue - itemList[currentIndex].weight
            currentIndex--
        }
        return itemList[currentIndex].index //Return the value once we can not go any further with our size
    }

}

/**
 * Your Solution object will be instantiated and called as such:
 * var obj = Solution(w)
 * var param_1 = obj.pickIndex()
 */