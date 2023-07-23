package algorithms.datastructures

import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.booleans.shouldBeFalse
import io.kotest.matchers.booleans.shouldBeTrue
import io.kotest.matchers.shouldBe

class ArrayStackTests : FunSpec({

    test("empty stack") {
        val stack = ArrayStack<Int>()
        stack.size.shouldBe(0)
        stack.capacity.shouldBe(16)
        stack.isEmpty().shouldBeTrue()
        shouldThrow<IllegalStateException> { stack.peek() }
        shouldThrow<IllegalStateException> { stack.pop() }
    }

    test("push an item") {
        val stack = ArrayStack<Int>()
        stack.push(10)
        stack.size.shouldBe(1)
        stack.capacity.shouldBe(16)
        stack.isEmpty().shouldBeFalse()
        stack.peek().shouldBe(10)

        stack.pop().shouldBe(10)
        stack.size.shouldBe(0)
    }

    test("resizing up") {
        val stack = ArrayStack<Int>(2)
        stack.capacity.shouldBe(2)

        stack.push(1)
        stack.push(2)
        stack.size.shouldBe(2)
        stack.capacity.shouldBe(2)

        stack.push(3)
        stack.size.shouldBe(3)
        stack.capacity.shouldBe(4)
    }

    test("resizing down") {
        val stack = ArrayStack<Int>(2)
        stack.capacity.shouldBe(2)

        stack.push(1)
        stack.push(2)
        stack.push(3)
        stack.size.shouldBe(3)
        stack.capacity.shouldBe(4)

        stack.pop().shouldBe(3)
        stack.size.shouldBe(2)
        stack.capacity.shouldBe(4)

        stack.pop().shouldBe(2)
        stack.size.shouldBe(1)
        stack.capacity.shouldBe(2)
    }

    test("iterate") {
        val stack = ArrayStack<Int>()

        for(i in 1..20) {
            stack.push(i)
        }

        var expected = 20
        for(item in stack) {
            item.shouldBe(expected)
            expected--
        }

        //iterator should not change the stack
        stack.size.shouldBe(20)
        stack.peek().shouldBe(20)
    }

    test("iterate and modify") {
        val stack = ArrayStack<Int>()
        stack.push(1)
        stack.push(2)

        var oneIterationHappened = false

        shouldThrow<ConcurrentModificationException> {
            for(item in stack) {
                item.shouldBe(2)
                oneIterationHappened = true
                stack.push(3)
            }
        }

        oneIterationHappened.shouldBeTrue()
    }

})