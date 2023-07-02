package algorithms

import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.shouldBe

class RationalTest : FunSpec({

    test("plus") {
     val r1 = Rational(2, 4)
     val r2 = Rational(3, 9)
     (r1 + r2).shouldBe(Rational(5, 6))
    }

    test("plus with overflow") {
        val r1 = Rational(1, 2)
        val r2 = Rational(1, Int.MAX_VALUE - 1)
        shouldThrow<IllegalArgumentException> {
            r1 + r2
        }
    }

    test("minus") {
     val r1 = Rational(1, 2)
     val r2 = Rational(1, 3)
     (r1 - r2).shouldBe(Rational(1, 6))
    }

    test("times") {
     val r1 = Rational(1, 3)
     val r2 = Rational(1, 3)
     (r1 * r2).shouldBe(Rational(1, 9))
    }

    test("div") {
     val r1 = Rational(1, 3)
     val r2 = Rational(1, 3)
     (r1 / r2).shouldBe(Rational(1, 1))
    }
})
