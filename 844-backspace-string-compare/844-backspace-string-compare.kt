/* (Linear time, linear space)
    val sStack = mutableListOf<Char>()
        val tStack = mutableListOf<Char>()
        
        s.forEach { char ->
            if (char == '#') {
                if (sStack.size > 0) sStack.removeAt(sStack.size - 1)
            } else {
                sStack.add(char)
            }
        }
        
        t.forEach { char ->
            if (char == '#') {
                if (tStack.size > 0) tStack.removeAt(tStack.size - 1)
            } else {
                tStack.add(char)
            }
        }
        
        if(sStack.size != tStack.size) return false
        sStack.forEachIndexed { index, char ->
            if(tStack.get(index) != char) return false
        }
        return true
*/


class Solution {
    fun backspaceCompare(s: String, t: String): Boolean {
        var sSkips = 0
        var tSkips = 0
        var sIndex = s.length - 1
        var tIndex = t.length - 1
        while(sIndex >= 0 || tIndex >= 0) {
            while(s.getOrNull(sIndex) == '#') {
                    sSkips++
                    sIndex--
                    while(sSkips > 0) {
                        if(s.getOrNull(sIndex) == '#') {
                            sSkips++
                        } else {
                            sSkips--
                        }
                        sIndex--
                    }
            }
            
            while(t.getOrNull(tIndex) == '#') {
                    tSkips++
                    tIndex--
                    while(tSkips > 0) {
                        if(t.getOrNull(tIndex) == '#') {
                            tSkips++
                        } else {
                            tSkips--
                        }
                        tIndex--
                    }
            }
            if(s.getOrNull(sIndex) != t.getOrNull(tIndex)) return false
            sIndex--
            tIndex--
        }
        return true
    }
}