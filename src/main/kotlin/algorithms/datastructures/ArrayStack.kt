@file:Suppress("UNCHECKED_CAST")

package algorithms.datastructures

import java.util.NoSuchElementException
import kotlin.math.max
import kotlin.math.min

class ArrayStack<T>(private val initialCapacity: Int = 16): Iterable<T> {
    private var a = arrayOfNulls<Any?>(initialCapacity)
    private var version = 0

    var capacity = initialCapacity
        private set

    var size = 0
        private set

    fun isEmpty() = size == 0

    fun push(item: T) {
        if (size == a.size) resize(2 * a.size)
        a[size] = item
        size++
        version++
    }

    fun pop(): T {
        if (size == 0) throw IllegalStateException("Unable to pop from the empty stack!")

        val item = a[size - 1]
        a[size - 1] = null
        size--
        version++

        // resize down if less than 1/4 is used, but don't fall down the initial capacity
        if (size <= capacity / 4) resize(max(initialCapacity, capacity / 2))

        return item as T
    }

    fun peek(): T {
        if (size == 0) throw IllegalStateException("Unable to peek from the empty stack!")

        return a[size - 1] as T
    }

    private fun resize(newCapacity: Int) {
        if (newCapacity != capacity) {
            capacity = newCapacity
            a = a.copyOf(capacity)
        }
    }

    override fun iterator(): Iterator<T> {
        return StackIterator(version)
    }

    inner class StackIterator<T>(private val versionAtCreate: Int): Iterator<T> {
        private var current = size - 1

        override fun hasNext(): Boolean = current >= 0

        override fun next(): T {
            if (current < 0) throw NoSuchElementException()
            if (versionAtCreate != version) throw ConcurrentModificationException()

            return a[current--] as T
        }
    }
}