package algorithms.datastructures

import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.booleans.shouldBeFalse
import io.kotest.matchers.booleans.shouldBeTrue
import io.kotest.matchers.shouldBe

class PriorityQueueTests : FunSpec({

    test("empty queue") {
        val stack = PriorityQueue()
        stack.size.shouldBe(0)
        stack.capacity.shouldBe(16)
        stack.isEmpty().shouldBeTrue()
        shouldThrow<IllegalStateException> { stack.takeMax() }
        shouldThrow<IllegalStateException> { stack.max }
    }

    test("insert an item") {
        val stack = PriorityQueue()
        stack.insert(10)
        stack.size.shouldBe(1)
        stack.isEmpty().shouldBeFalse()
        stack.max.shouldBe(10)

        stack.takeMax().shouldBe(10)
        stack.size.shouldBe(0)
    }

    test("multiple items") {
        val stack = PriorityQueue()

        stack.insert(20)
        stack.insert(40)
        stack.insert(10)
        stack.insert(50)
        stack.insert(30)
        stack.size.shouldBe(5)

        stack.max.shouldBe(50)
        stack.takeMax().shouldBe(50)
        stack.size.shouldBe(4)

        stack.max.shouldBe(40)
        stack.takeMax().shouldBe(40)
        stack.takeMax().shouldBe(30)
        stack.takeMax().shouldBe(20)
        stack.takeMax().shouldBe(10)
        stack.size.shouldBe(0)
    }

    test("resizing") {
        val stack = PriorityQueue(2)
        stack.capacity.shouldBe(2)

        stack.insert(1)
        stack.insert(2)
        stack.size.shouldBe(2)
        stack.capacity.shouldBe(2)

        stack.insert(3)
        stack.size.shouldBe(3)
        stack.capacity.shouldBe(4)
    }

})