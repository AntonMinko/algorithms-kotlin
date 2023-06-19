package utils

inline fun IntArray.swap(i: Int, j: Int) {
    //this[i] = this[j].also { this[j] = this[i] }
    val tmp = this[i]
    this[i] = this[j]
    this[j] = tmp
}