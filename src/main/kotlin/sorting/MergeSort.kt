package sorting

public fun IntArray.mergeSort() {
    this.asList().mergeSort().toIntArray().copyInto(this)
    //this.asList().mergeSort().forEachIndexed { i, el -> this[i] = el }
}

private fun List<Int>.mergeSort(): List<Int> {
    if (size < 2) return this
    if (size == 2 && this[0] < this[1]) return this

    val left = this.subList(0, size / 2).mergeSort()
    val right = this.subList(size / 2, size).mergeSort()

    return merge(left, right)
}

inline fun merge(left: List<Int>, right: List<Int>): List<Int> {
    val sorted = MutableList(left.size + right.size) { 0 }
    var l = 0
    var r = 0
    var s = 0

    while(l < left.size || r < right.size) {
        if (l == left.size || (r < right.size && left[l] > right[r])) {
            sorted[s] = right[r]
            r++
        }
        else {
            sorted[s] = left[l]
            l++
        }
        s++
    }
    return sorted
}
