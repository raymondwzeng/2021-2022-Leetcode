class Solution {
    
    data class HeapNode(val string: String, val freq: Int): Comparable<HeapNode> {
        override fun compareTo(other: HeapNode): Int {
            return when {
                this.freq == other.freq -> { //If they have the same frequency, we also want to compare the strings
                    this.string.compareTo(other.string)
                }
                this.freq < other.freq -> 1
                else -> -1
            }
        }
    }
    
    fun topKFrequent(words: Array<String>, k: Int): List<String> {
        /*
            Heap
            n -> # of words within our input, k -> # of UNIQUE words within our input
            - Create a frequency map of each unique word MutableMap<String, Int> where int is freq of the string -> O(n) time, O(k) space
            - For each element within the frequency map, add the item into the heap (maxheap) -> O(k) * O(log(k)) -> O(klogk) time, O(k) space
            - For 0..k-1, poll an element from the heap -> O(klogk) time
        */
        val freqMap = mutableMapOf<String, Int>()
        val heap = PriorityQueue<HeapNode>()
        words.forEach { word ->
            freqMap.put(word, (freqMap.getOrPut(word, {0}) as Int) + 1)
        }
        
        freqMap.forEach { entry -> heap.add(HeapNode(entry.key, entry.value))}
        
        val returnList = mutableListOf<String>()
        
        //Problem: We need to also consider the lexicographical ordering of the items
        /* Potential solution: We let the heap do the work for us
            - By default, the heap preserves insertion order, if the comparison is equal
            - However, we could also add into our compareTo overload that we want to consider also the lexicographical order of the string
            - Thus when pulling out elements from our heap, we get them in the right order
        */
        
        for(i in 0..k-1) {
            returnList.add(heap.poll().string)
        }
        
        return returnList
    }
}