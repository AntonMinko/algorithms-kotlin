package sorting

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

private fun IntArray.swap(i: Int, j: Int) {
    this[i] = this[j].also { this[j] = this[i] }
}