package sorting

import utils.swap

public fun IntArray.insertionSort() {
    for(i in 1 until size) {
        for(j in i downTo 1) {
            val cur = this[j]
            if (this[j-1] > cur) {
                this[j] = this[j-1]
            }
            else {
                this[j] = cur
                break
            }
        }
    }
}
