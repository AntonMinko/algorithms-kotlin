package sorting

import utils.swap

public fun IntArray.insertionSort() {
    for(i in 1 until size) {
        for(j in (1..i).reversed()) {
            if (this[j] < this[j-1]) {
                swap(j, j-1)
            }
            else {
                break
            }
        }
    }
}
