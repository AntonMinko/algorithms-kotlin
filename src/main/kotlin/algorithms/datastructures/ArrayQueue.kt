@file:Suppress("UNCHECKED_CAST")

package algorithms.datastructures

class ArrayQueue<T>(private val initialCapacity: Int = 16): Iterable<T> {
    private var a = arrayOfNulls<Any?>(initialCapacity)
    private var version = 0
    private var head = -1
    private var tail = 0

    var capacity = initialCapacity
        private set

    var size = 0
        private set

    fun isEmpty() = size == 0

    fun enqueue(value: T) {
        if (size == capacity) resize(capacity * 2)

        head = head.incrementIndex()
        a[head] = value
        size++
        version++
    }

    fun dequeue(): T {
        if (size == 0) throw IllegalStateException("Queue is empty")

        val value = a[tail] as T
        a[tail] = null
        tail = tail.incrementIndex()
        size--
        version++

        if (size * 4 <= capacity) resize(capacity / 2)

        return value
    }

    fun peek(): T {
        if (size == 0) throw IllegalStateException("Queue is empty")

        return a[tail] as T
    }

    private fun Int.incrementIndex() = (this + 1) % capacity

    private fun resize(newCapacity: Int) {
        val newA = arrayOfNulls<Any?>(newCapacity)

        for(i in 0 until size) {
            val j = (tail + i) % capacity
            newA[i] = a[j]
        }

        a = newA
        capacity = newCapacity
        tail = 0
        head = size - 1
    }

    override fun iterator(): Iterator<T> = QueueIterator(version)

    inner class QueueIterator<T>(private val versionAtCreate: Int): Iterator<T> {
        var nextInd = tail
        var iterated = 0
        override fun hasNext(): Boolean = iterated < size

        override fun next(): T {
            if (versionAtCreate != version) throw ConcurrentModificationException()
            if (iterated >= size) throw NoSuchElementException()

            val value = a[nextInd] as T
            nextInd = nextInd.incrementIndex()
            iterated++

            return value
        }

    }
}