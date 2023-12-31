package sorting

import utils.insertionSort
import kotlin.math.min

public fun IntArray.mergeSortBottomUp() {
    val aux = IntArray(size)

    var i = 0
    while (i < size) {
        this.insertionSort(i, min(i + 15, size - 1))
        i += 16
    }
    i = 16
    while(i < size) {
        for(j in 0 until size - i step 2 * i) {
            merge(this, aux, j, j + i - 1, min(j + 2 * i - 1, size - 1))
        }
        i *= 2
    }
}

private fun merge(arr: IntArray, aux: IntArray, low: Int, mid: Int, hi: Int) {
    if (arr[mid] <= arr[mid+1]) return

    for(i in low..hi) {
        aux[i] = arr[i]
    }

    var l = low
    var r = mid + 1

    for(i in low..hi) {
        if (l > mid) {
            arr[i] = aux[r]
            r++
        }
        else if (r > hi) {
            arr[i] = aux[l]
            l++
        }
        else if (aux[l] > aux[r]) {
            arr[i]= aux[r]
            r++
        }
        else {
            arr[i] = aux[l]
            l++
        }
    }
}
