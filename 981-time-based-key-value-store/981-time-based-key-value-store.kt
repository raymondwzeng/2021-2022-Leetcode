class TimeMap() {

    data class DataNode(val value: String, val timestamp: Int){}
    /*
        Set inserts a key, and a value at a specific timestamp
        
        Get returns:
            -> The most recent item
            -> No item at all (if an item does not exist of that key, or if no items have a timestamp <= provided timestamp)
        
        We want to optimize for this get operation, such that we don't take long to retrieve the max
        
        Base DS will be a hashmap, with the key as a string
            -> The value will also be some sort of data structure
            The straightforward answer is a list of nodes, where each node holds a value and a timestamp
            Every get request will scan through the list of nodes, and collect the maximum with a timestamp less than provided. -> O(n)
            
        We need something sequential -> Array, LinkedList, Binary Tree, Heap, etc.
        Every item that we insert will be the item that we actually want to retrieve...not quite
        Insertions will always be made (probably) to the end of a data structure
            -Lists *may* be possible to optimize using binary search -> O(logn) with some complications
                Start with the middle of our list in terms of timestamps
                If middle == timestamp, we're done
                If middle < timestamp, keep it in mind, but go to the right side
                If middle > timestamp, go to the left side
            -Maxheap of some sort -> We know that the items underneath the current layer will be less
                ->Binary Search Tree would guarantee some sort of order between left and right trees -> O(logn), with also complications
    */
    val dataMap = mutableMapOf<String, MutableList<DataNode>>()
    
    fun set(key: String, value: String, timestamp: Int) {
        if(dataMap.get(key) != null) {
            val list = dataMap.get(key) as MutableList<DataNode>
            list.add(DataNode(value, timestamp))
        } else {
            dataMap.put(key, mutableListOf<DataNode>(DataNode(value, timestamp)))
        }
    }
    
    private fun get(list: List<DataNode>, timestamp: Int, left: Int, right: Int): String {
        if(left >= right) return ""
        val middle = (right + left) / 2
        val middleNode = list[middle]
        // println(middleNode)
        when {
            middleNode.timestamp == timestamp -> return middleNode.value
            middleNode.timestamp < timestamp -> {
                //Save our current value, compute a potentially newer value, and check to make sure that it isn't ""
                val rightResult = get(list, timestamp, middle + 1, right)
                if(rightResult.isNotEmpty()) return rightResult else return middleNode.value
            }
            else -> return get(list, timestamp, left, middle)
        }
    }

    fun get(key: String, timestamp: Int): String {
        if(dataMap.get(key) == null) return ""
        // println("Getting closest stamp to $timestamp of key $key")
        val innerList = dataMap.get(key) as List<DataNode>
        return get(innerList, timestamp, 0, innerList.size)
    }

}

/**
 * Your TimeMap object will be instantiated and called as such:
 * var obj = TimeMap()
 * obj.set(key,value,timestamp)
 * var param_2 = obj.get(key,timestamp)
 */