class LRUCache(val capacity: Int) {
    
    data class ListNode(val key: Int, var value: Int, var prev: ListNode?, var next: ListNode?)
    
    //ALL operations must run in constant time
    /*
        Main problem:
        When we update an element within our cache, we need to figure out how to move that item from its current position in the LRU
        queue to the back.
        
        Shifting elements from some middle point to the back -> LinkedList
        
        Secondary problem:
        Okay, let's use a linkedlist. How do we figure out the position of the item that has the key that we want?
        
        Solution:
        HashMap<Int, ListNode> Which will hold a reference to any given listnode with a key
        LinkedList Which will allow us to move items from some arbitrary middle to the end if we decide to update
        
        currentCapacity -> Int variable, that when met, will simply turn all puts into replacements
    */
    var currentCapacity = 0
    val referenceMap = mutableMapOf<Int, ListNode>()
    val list = LinkedList<ListNode>()
    
    var head: ListNode? = null
    var tail: ListNode? = null

    fun get(key: Int): Int {
        if(referenceMap.get(key) != null) {
            val node = referenceMap.get(key) as ListNode
            moveToBack(node)
            return node.value
        } else {
            return -1
        }
    }

    fun put(key: Int, value: Int) {
        val newNode = ListNode(key, value, null, null)
        if(referenceMap.get(key) == null) {
            when(currentCapacity) {
            0 -> { //Add and set head case
                head = newNode
                tail = newNode
            }
            else -> { //Add/evict case
                if(currentCapacity == capacity) {
                    referenceMap.remove(head?.key)
                    val temp : ListNode? = head?.next
                    temp?.prev = null
                    head?.next = null
                    head = temp
                    if(head == null) head = newNode
                }
                tail?.next = newNode
                newNode?.prev = tail
                tail = newNode
                }
            }
            if(currentCapacity < capacity) currentCapacity++
            list.add(newNode)
            referenceMap.put(key, newNode)
        } else {
            val node = referenceMap.get(key)!!
            node.value = value
            moveToBack(node)
        }
    }
    
    private fun moveToBack(node: ListNode) {
        if(tail == node) return
        if(head == node) head = node.next
        
        node.prev?.next = node.next
        node.next?.prev = node.prev
        
        tail?.next = node
        node.prev = tail
        node.next = null
        tail = node
    }

}

/**
 * Your LRUCache object will be instantiated and called as such:
 * var obj = LRUCache(capacity)
 * var param_1 = obj.get(key)
 * obj.put(key,value)
 */