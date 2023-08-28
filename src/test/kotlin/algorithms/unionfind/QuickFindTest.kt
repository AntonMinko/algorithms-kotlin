package algorithms.unionfind

import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.booleans.shouldBeFalse
import io.kotest.matchers.booleans.shouldBeTrue
import io.kotest.matchers.shouldBe

class QuickFindTest : FunSpec({
    context("one node") {
        test("single node") {
            val sut = QuickFind(1)
            sut.count.shouldBe(1)
            sut.connected(0, 0).shouldBeTrue()
        }
    }

    context("two nodes") {
        test("two components") {
            val sut = QuickFind(2)
            sut.count.shouldBe(2)
            sut.connected(0, 1).shouldBeFalse()
        }

        test("one component") {
            val sut = QuickFind(2)
            sut.union(0, 1)
            sut.count.shouldBe(1)
            sut.connected(0, 1).shouldBeTrue()
            sut.find(0).shouldBe(sut.find(1))
        }
    }

    context("three nodes") {
        test("three components") {
            val sut = QuickFind(3)
            sut.count.shouldBe(3)
            sut.connected(0, 1).shouldBeFalse()
        }

        test("two components") {
            val sut = QuickFind(3)
            sut.union(1, 2)
            sut.connected(1, 2).shouldBeTrue()
            sut.count.shouldBe(2)
        }

        test("one component") {
            val sut = QuickFind(3)
            sut.union(0, 1)
            sut.union(2, 0)
            sut.count.shouldBe(1)
            sut.connected(2, 1).shouldBeTrue()
        }

        test("one component, extra nodes") {
            val sut = QuickFind(3)
            sut.union(0, 1)
            sut.union(1, 2)
            sut.union(2, 0)
            sut.union(2, 1)
            sut.count.shouldBe(1)
        }
    }
})