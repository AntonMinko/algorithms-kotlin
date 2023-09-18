package utils

inline fun IntArray.swap(i: Int, j: Int) {
    if (i == j) return

    val tmp = this[i]
    this[i] = this[j]
    this[j] = tmp
}

fun IntArray.insertionSort(low: Int, hi: Int) {
    for(i in low + 1..hi) {
        val cur = this[i]
        for(j in i downTo low) {
            if (j == low || this[j-1] <= cur) {
                this[j] = cur
                break
            }
            else {
                this[j] = this[j-1]
            }
        }
    }
}