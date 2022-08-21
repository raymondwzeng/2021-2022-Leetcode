class WordDictionary() {
    //Trie problem?
    //All trie strings should end with a terminating character -> '-'
    data class TrieNode(val char: Char, val children: Array<TrieNode?>)
    
    val trieRoot = Array<TrieNode?>(26){ null }

    fun addWord(word: String) {
        var root: TrieNode? = null
        word.forEachIndexed { index, char ->
            if(index == 0) {
                if(trieRoot.getOrNull(char - 'a') == null) {
                    trieRoot.set(char - 'a', TrieNode(char, Array<TrieNode?>(27){null}))
                }
                root = trieRoot[char - 'a']
                // println("Added $root as a root node to TrieRoot")
            } else {
                if(root?.children?.getOrNull(char - 'a') == null) {
                    root?.children?.set(char - 'a', TrieNode(char, Array<TrieNode?>(27){null}))
                }
                // println("New root @ ${root?.children?.getOrNull(char - 'a')} as a child of $root")
                root = root?.children?.getOrNull(char - 'a')
            }
        }
        root?.children?.set(26, TrieNode('-', Array<TrieNode?>(0){null}))
    }

    fun search(word: String): Boolean {
        //If we start with a wildcard operator, then we need to search all of the tries for a word
        //Otherwise, it's not *as* bad?
        val queue = LinkedList<TrieNode>()
        word.forEachIndexed { index, char ->
            if(char == '.') {
                if(index == 0) {
                    trieRoot.forEach { root -> if(root != null) queue.add(root) }
                    if(queue.isEmpty()) return false //If there are no roots with a wildcard
                } else {
                   for(i in 0..queue.size - 1) {
                        val front = queue.poll()
                        front.children.forEach { node ->
                            if(node != null) queue.add(node)
                        }
                   }
                   if(queue.isEmpty()) return false
                }
            } else {
                if(index == 0) {
                    if(trieRoot.getOrNull(char - 'a') != null) {
                        queue.add(trieRoot.get(char - 'a') as TrieNode)
                    } else {
                        return false
                    }
                } else {
                    for(i in 0..queue.size - 1) {
                        val front = queue.poll()
                        if(front.children.getOrNull(char - 'a') != null) {
                            queue.add(front.children.get(char - 'a') as TrieNode)
                        }
                    }
                }
                if(queue.isEmpty()) return false
            }
        }
        while(!queue.isEmpty()) {
            val front = queue.poll()
            if(front.children.getOrNull(26) != null) return true
        }
        return false
    }

}

/**
 * Your WordDictionary object will be instantiated and called as such:
 * var obj = WordDictionary()
 * obj.addWord(word)
 * var param_2 = obj.search(word)
 */