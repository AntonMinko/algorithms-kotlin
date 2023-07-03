package sorting

import utils.swap

public fun IntArray.radixSort(base: Int = 10) {
    val negativesSorted = this
        .filter { it < 0 }
        .map { -it }
        .toList()
        .let { positiveRadixSort(it, base) }
        .map { -it }
        .reversed()
    val positivesSorted = this
        .filter { it >= 0 }
        .toList()
        .let { positiveRadixSort(it, base) }

    negativesSorted.plus(positivesSorted).toIntArray().copyInto(this)
}

private fun positiveRadixSort(unordered: List<Int>, base: Int): List<Int> {
    var list = unordered
    val digits = MutableList(base) { mutableListOf<Int>() }

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