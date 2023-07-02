package algorithms

class Rational {
    val numerator: Int
    val denumerator: Int

    constructor(numerator: Int, denumerator: Int) {
        if (numerator <= 0) throw IllegalArgumentException("Numerator must be greater than 0!")
        if (denumerator <= 0) throw IllegalArgumentException("Denumerator must be greater than 0!")

        val commonFactor = biggestCommonFactor(numerator, denumerator)
        this.numerator = numerator / commonFactor
        this.denumerator = denumerator / commonFactor
    }

    companion object {
        private fun from(numerator: Long, denumerator: Long): Rational {
            if (numerator > Int.MAX_VALUE) throw IllegalArgumentException("Numerator must be less than ${Int.MAX_VALUE}, but was $numerator!")
            if (denumerator > Int.MAX_VALUE) throw IllegalArgumentException("Denumerator must be less than ${Int.MAX_VALUE}, but was $denumerator!")

            return Rational(numerator.toInt(), denumerator.toInt())
        }
    }
    operator fun plus(other: Rational) = Rational.from(
        numerator.toLong() * other.denumerator + other.numerator.toLong() * denumerator,
        denumerator.toLong() * other.denumerator
    )

    operator fun minus(other: Rational) = Rational.from(
        numerator.toLong() * other.denumerator - other.numerator.toLong() * denumerator,
        denumerator.toLong() * other.denumerator
    )

    operator fun times(other: Rational) = Rational.from(
        numerator.toLong() * other.numerator,
        denumerator.toLong() * other.denumerator
    )

    operator fun div(other: Rational) = Rational.from(
        numerator.toLong() * other.denumerator,
        denumerator.toLong() * other.numerator
    )

    override fun equals(other: Any?): Boolean =
        other is Rational && numerator == other.numerator && denumerator == other.denumerator

    override fun hashCode() = numerator.hashCode() * 31 + denumerator.hashCode()

    override fun toString() = "$numerator/$denumerator"

    private fun biggestCommonFactor(n1: Int, n2: Int): Int {
        if (n2 == 0) return n1
        val rem = n1 % n2
        return biggestCommonFactor(n2, rem)
    }
}