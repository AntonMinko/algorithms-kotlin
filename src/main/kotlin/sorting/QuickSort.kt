package sorting

public fun IntArray.quickSort() = this.toList().quickSortExternal().toIntArray().copyInto(this)

private fun List<Int>.quickSortExternal(): List<Int> {
    if (this.size < 2) return this

    val pivotInd = size / 2
    val pivot = this[pivotInd]
    val left = this.filter { it < pivot }
    val mid = this.filter { it == pivot }
    val right = this.filter { it > pivot }

    return left.quickSortExternal()
        .plus(mid)
        .plus(right.quickSortExternal())
}