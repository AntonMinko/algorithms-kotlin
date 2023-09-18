package sorting

fun IntArray.insertionSortOptimized() {
    for(i in 1 until size) {
        val cur = this[i]
        for(j in i downTo 0) {
            if (j == 0 || this[j-1] <= cur) {
                this[j] = cur
                break
            }
            else {
                this[j] = this[j-1]
            }
        }
    }
}
