package sorting

import utils.swap

enum class Strategy {
    EXTERNAL,
    LUMOTO,
    HOARE
}
public fun IntArray.quickSort(strategy: Strategy) {
    when(strategy) {
        Strategy.EXTERNAL -> this.toList().quickSortExternal().toIntArray().copyInto(this)
        Strategy.LUMOTO -> quickSortLumoto()
        Strategy.HOARE -> quickSortHoare()
    }

}

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

private fun IntArray.quickSortLumoto(low: Int = 0, high: Int = lastIndex) {
    if (low >= high) return

    val pivot = this[high]
    var i = low
    for(j in low until high) {
        if (this[j] <= pivot) {
            swap(i, j)
            i++
        }
    }
    swap(i, high)
    quickSortLumoto(low, i - 1)
    quickSortLumoto(i + 1, high)
}

private fun IntArray.quickSortHoare(low: Int = 0, high: Int = lastIndex) {
    if (low >= high) return

    val pivot = this[low]
    var i = low - 1
    var j = high + 1

    while (i < j) {
        do {
            i++
        }
        while (this[i] < pivot)

        do {
            j--
        }
        while (this[j] > pivot)

        if (i < j) {
            swap(i, j)
        }
    }

    quickSortHoare(low, j)
    quickSortHoare(j + 1, high)
}
