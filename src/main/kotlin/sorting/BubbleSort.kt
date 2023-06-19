package sorting

import utils.swap

public fun IntArray.bubbleSort() {
    for(i in indices) {
        var modified = false
        for(j in 0 until lastIndex - i) {
            if (this[j] > this[j + 1]) {
                swap(j, j + 1)
                modified = true
            }
        }
        if (!modified) return
    }
}
