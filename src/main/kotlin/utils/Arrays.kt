package utils

import kotlin.random.Random
import java.io.File

fun generateArrays(min: Int, max: Int, step: Int) = sequence {
    for(n in min..max step step) {
        yield(IntArray(n) { Random.nextInt(-n, n) })
    }
}

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
