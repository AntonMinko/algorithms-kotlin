package sorting

import utils.swap

public fun IntArray.radixSort() {
    val negativesSorted = this
        .filter { it < 0 }
        .map { -it }
        .toList()
        .let { positiveRadixSort(it) }
        .map { -it }
        .reversed()
    val positivesSorted = this
        .filter { it >= 0 }
        .toList()
        .let { positiveRadixSort(it) }

    negativesSorted.plus(positivesSorted).toIntArray().copyInto(this)
}

private fun positiveRadixSort(unordered: List<Int>): List<Int> {
    val base = 10
    var list = unordered
    val digits = MutableList(10) { mutableListOf<Int>() }

    var step = 1
    var done = false

    while(!done) {
        done = true
        for(i in list.indices) {
            val num = list[i] / step
            val digit = num % base
            digits[digit].add(list[i])

            if (digit != 0) done = false
        }
        list = digits.flatten()
        digits.forEach { it.clear() }
        step *= base
    }

    return list
}