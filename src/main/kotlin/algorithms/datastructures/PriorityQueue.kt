package algorithms.datastructures

import utils.swap

class PriorityQueue(private val initialCapacity: Int = 16) {
    private var pq: IntArray = IntArray(initialCapacity + 1)

    var capacity: Int = initialCapacity
        private set

    var size: Int = 0
        private set

    fun isEmpty() = size == 0

    fun insert(item: Int) {
        if (size == capacity) {
            capacity *= 2
            pq = pq.copyInto(IntArray(capacity + 1))
        }

        size++
        pq[size] = item
        swim(size)
    }

    val max: Int
        get() = if (size > 0) pq[1] else throw IllegalStateException()

    fun takeMax(): Int {
        if (size == 0) throw IllegalStateException()

        val max = pq[1]
        pq[1] = pq[size]
        size--
        sink(1)

        return max
    }

    private fun swim(ind: Int) {
        var i = ind
        var p = i / 2
        while (p >= 1 && pq[p] < pq[i]) {
            pq.swap(p, i)
            i = p
            p /= 2
        }
    }

    private fun sink(ind: Int = 1) {
        var p = ind
        var i = 2 * p
        while(i <= size) {
            if (i + 1 <= size && pq[i] < pq[i+1]) {
                i++
            }
            if (pq[p] < pq[i]) {
                pq.swap(p, i)
                p = i
                i *= 2
            }
            else {
                break
            }
        }
    }
}