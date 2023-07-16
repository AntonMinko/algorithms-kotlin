package utils

import kotlin.random.Random
import java.io.File

fun generateArrays(min: Int, max: Int, step: Int) = sequence {
    for(n in min..max step step) {
        yield(generateArray(n, -n, n))
    }
}

fun generateArray(size: Int, min: Int, max: Int) = IntArray(size) { Random.nextInt(min, max) }

fun Sequence<IntArray>.serializeTo(path: String) {
    File(path).delete()
    this.forEach { array ->
        File(path).appendText(array.joinToString(postfix = "\n"))
    }
}

fun File.deserializeToIntArrays(): Sequence<IntArray> = sequence {
    for (line in this@deserializeToIntArrays.readLines()) {
        yield(
            line.split(", ")
                .map(String::toInt)
                .toIntArray()
        )
    }
}
