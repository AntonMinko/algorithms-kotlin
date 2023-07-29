package algorithms.datastructures

import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.booleans.shouldBeFalse
import io.kotest.matchers.booleans.shouldBeTrue
import io.kotest.matchers.shouldBe

class ArrayQueueTests : FunSpec({

    test("empty queue") {
        val stack = ArrayQueue<Int>()
        stack.size.shouldBe(0)
        stack.capacity.shouldBe(16)
        stack.isEmpty().shouldBeTrue()
        shouldThrow<IllegalStateException> { stack.peek() }
        shouldThrow<IllegalStateException> { stack.dequeue() }
    }

    test("enqueue an item") {
        val stack = ArrayQueue<Int>()
        stack.enqueue(10)
        stack.size.shouldBe(1)
        stack.capacity.shouldBe(16)
        stack.isEmpty().shouldBeFalse()
        stack.peek().shouldBe(10)

        stack.dequeue().shouldBe(10)
        stack.size.shouldBe(0)
    }

    test("resizing up") {
        val stack = ArrayQueue<Int>(2)
        stack.capacity.shouldBe(2)

        stack.enqueue(1)
        stack.enqueue(2)
        stack.size.shouldBe(2)
        stack.capacity.shouldBe(2)

        stack.enqueue(3)
        stack.size.shouldBe(3)
        stack.capacity.shouldBe(4)
    }

    test("resizing down") {
        val stack = ArrayQueue<Int>(2)
        stack.capacity.shouldBe(2)

        stack.enqueue(1)
        stack.enqueue(2)
        stack.enqueue(3)
        stack.size.shouldBe(3)
        stack.capacity.shouldBe(4)

        stack.dequeue().shouldBe(1)
        stack.size.shouldBe(2)
        stack.capacity.shouldBe(4)

        stack.dequeue().shouldBe(2)
        stack.size.shouldBe(1)
        stack.capacity.shouldBe(2)
    }

    test("iterate") {
        val stack = ArrayQueue<Int>()

        for(i in 1..20) {
            stack.enqueue(i)
        }

        var expected = 1
        for(item in stack) {
            item.shouldBe(expected)
            expected++
        }

        //iterator should not change the stack
        stack.size.shouldBe(20)
        stack.peek().shouldBe(1)
    }

    test("iterate and modify") {
        val stack = ArrayQueue<Int>()
        stack.enqueue(1)
        stack.enqueue(2)

        var oneIterationHappened = false

        shouldThrow<ConcurrentModificationException> {
            for(item in stack) {
                item.shouldBe(1)
                oneIterationHappened = true
                stack.enqueue(3)
            }
        }

        oneIterationHappened.shouldBeTrue()
    }

})