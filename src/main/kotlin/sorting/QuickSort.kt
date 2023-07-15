package sorting

import utils.swap

enum class Strategy {
    EXTERNAL,
    LUMOTO,
    LUMOTO_MEDIAN,
    LUMOTO_MEDIAN_DUPLICATES,
    HOARE
}
public fun IntArray.quickSort(strategy: Strategy) {
    when(strategy) {
        Strategy.EXTERNAL -> this.toList().quickSortExternal().toIntArray().copyInto(this)
        Strategy.LUMOTO -> quickSortLumoto()
        Strategy.LUMOTO_MEDIAN -> quickSortLumotoMedian()
        Strategy.LUMOTO_MEDIAN_DUPLICATES -> quickSortLumotoMedianDuplicates()
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

private fun IntArray.quickSortLumotoMedian(low: Int = 0, high: Int = lastIndex) {
    if (low >= high) return

    val pivot = medianPivot(low, high)
    var i = low
    for(j in low until high) {
        if (this[j] <= pivot) {
            swap(i, j)
            i++
        }
    }
    swap(i, high)
    quickSortLumotoMedian(low, i - 1)
    quickSortLumotoMedian(i + 1, high)
}

private fun IntArray.quickSortLumotoMedianDuplicates(low: Int = 0, high: Int = lastIndex) {
    if (low >= high) return

    val pivot = medianPivot(low, high)
    var smaller = low
    var equal = low
    var larger = high
    while(equal <= larger) {
        if (this[equal] == pivot) {
            equal++
        }
        else if (this[equal] < pivot) {
            swap(smaller, equal)
            smaller++
            equal++
        }
        else {
            swap(equal, larger)
            larger--
        }
    }

    quickSortLumotoMedianDuplicates(low, smaller - 1)
    quickSortLumotoMedianDuplicates(equal, high)
}

private inline fun IntArray.medianPivot(low: Int, high: Int): Int {
    // move min of three element to low, and median element to high
    val mid = low + (high - low) / 2
    if (this[low] > this[mid]) {
        swap(low, mid)
    }
    if (this[low] > this[high]) {
        swap(low, high)
    }
    if (this[mid] < this[high]) {
        swap(mid, high)
    }
    return this[high]
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
