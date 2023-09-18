package utils

inline fun IntArray.swap(i: Int, j: Int) {
    if (i == j) return

    val tmp = this[i]
    this[i] = this[j]
    this[j] = tmp
}

fun IntArray.insertionSort(low: Int, hi: Int) {
    for(i in low + 1..hi) {
        for(j in i downTo low + 1) {
            val cur = this[j]
            if (this[j-1] > cur) {
                this[j] = this[j-1]
            }
            else {
                this[j] = cur
                break
            }
        }
    }
}