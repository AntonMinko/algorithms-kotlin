package sorting

import utils.swap

public fun IntArray.mergeSortTopDown() {
    val aux = IntArray(size)
    sort(this, aux, 0, size - 1)
}

private fun sort(arr: IntArray, aux: IntArray, low: Int, hi: Int) {
    if (hi - low <= 15) {
        insertionSort(arr, low, hi)
        return
    }

    val mid = low + (hi - low) / 2

    sort(arr, aux, low, mid)
    sort(arr, aux, mid + 1, hi)

    return merge(arr, aux, low, mid, hi)
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

private fun insertionSort(arr: IntArray, low: Int, hi: Int) {
    for(i in low + 1..hi) {
        for(j in i downTo low + 1) {
            if (arr[j] < arr[j-1]) {
                arr.swap(j, j-1)
            }
            else {
                break
            }
        }
    }
}