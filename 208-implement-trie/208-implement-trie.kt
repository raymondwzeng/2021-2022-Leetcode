class Trie() {
    
    class TrieNode(
        val char: Char,
        val neighbors : MutableList<TrieNode> //Could use an array, only have <= 26 children
    )
    
    val roots = Array<TrieNode?>(26) { null }

    fun insert(word: String) {
        var parentNode = roots[word[0] - 'a'] //Will always be one letter behind
        word.forEachIndexed { index, char -> //Assume that we do not have to deal with a word insertion that starts with a diff letter
            if(roots[word[0] - 'a'] == null) {
                roots[word[0] - 'a'] = TrieNode(char, mutableListOf<TrieNode>())
                parentNode = roots[word[0] - 'a']
            } else if(index > 0) {
                var hasFoundNeighbor = false
                parentNode?.neighbors?.forEach { node ->
                    if(node.char == char) {
                        hasFoundNeighbor = true
                        parentNode = node
                    }
                }
                if(!hasFoundNeighbor) {
                    val newNode = TrieNode(char, mutableListOf<TrieNode>())
                    parentNode?.neighbors?.add(newNode)
                    parentNode = newNode
                }
            }
        }
        parentNode?.neighbors?.add(TrieNode('-', mutableListOf<TrieNode>()))
    }
    
    private fun search(word: String, isPrefixMode: Boolean): Boolean {
        if(roots[word[0] - 'a'] == null) return false
        var currentNode = roots[word[0] - 'a']
        word.forEachIndexed { index, char ->
            if(currentNode?.char == char) {
                if(index < word.length - 1) {
                    var hasSwitched = false
                    currentNode?.neighbors?.forEach { neighbor ->
                        if(neighbor.char == word[index + 1]) {
                            hasSwitched = true
                            currentNode = neighbor
                        }
                    }
                    if(!hasSwitched) return false
                } else {
                    if(isPrefixMode) return true
                    currentNode?.neighbors?.forEach { node ->
                        if(node.char == '-') return true
                    }
                    return false
                }
            } else {
                return false
            }
        }
        return true
    }

    fun search(word: String): Boolean {
        val didFind = search(word, false)
        return didFind
    }

    fun startsWith(prefix: String): Boolean {
        val didFind = search(prefix, true)
        return didFind
    }

}

/**
 * Your Trie object will be instantiated and called as such:
 * var obj = Trie()
 * obj.insert(word)
 * var param_2 = obj.search(word)
 * var param_3 = obj.startsWith(prefix)
 */