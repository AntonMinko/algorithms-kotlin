package utils

inline fun IntArray.swap(i: Int, j: Int) {
    if (i == j) return

    val tmp = this[i]
    this[i] = this[j]
    this[j] = tmp
}