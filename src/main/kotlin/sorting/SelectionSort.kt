package sorting

public fun IntArray.selectionSort() {
    for(i in 0..lastIndex-1) {
        var minJ = i
        for(j in i+1..lastIndex) {
            if (this[minJ] > this[j]) minJ = j
        }
        if (minJ != i) swap(i, minJ)
    }
}

private fun IntArray.swap(i: Int, j: Int) {
    this[i] = this[j].also { this[j] = this[i] }
}