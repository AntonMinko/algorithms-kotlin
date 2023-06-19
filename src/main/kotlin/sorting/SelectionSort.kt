package sorting

import utils.swap

public fun IntArray.selectionSort() {
    for(i in 0..lastIndex-1) {
        var minJ = i
        for(j in i+1..lastIndex) {
            if (this[minJ] > this[j]) minJ = j
        }
        if (minJ != i) swap(i, minJ)
    }
}
