package sorting

import utils.swap

public fun IntArray.radixSort(base: Int = 10) {
    positiveRadixSort(this.toList(), base).toIntArray().copyInto(this)
}

private fun positiveRadixSort(unordered: List<Int>, base: Int): List<Int> {
    var list = unordered
    val digits = MutableList(base) { mutableListOf<Int>() }

    var step = 1
    var done = false

    while(!done) {
        done = true
        for(i in list.indices) {
            val isNegative = list[i] < 0
            val shifted = if (isNegative) -list[i] / step else list[i] / step
            val digit = if (isNegative) base - shifted % base - 1 else shifted % base

            digits[digit].add(list[i])

            if (shifted != 0) done = false
        }
        list = digits.flatten()
        digits.forEach { it.clear() }
        step *= base
    }

    for(el in list) {
        if (el < 0) {
            digits[0].add(el)
        }
        else {
            digits[1].add(el)
        }
    }

    return digits.flatten()
}