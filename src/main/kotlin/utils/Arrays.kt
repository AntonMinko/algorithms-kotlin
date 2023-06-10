package utils

import kotlin.random.Random
import java.io.File

fun generateArrays(min: Int, max: Int, step: Int): List<IntArray> {
    val arrays = mutableListOf<IntArray>()
    for(n in min..max step step) {
        arrays.add(IntArray(n) { Random.nextInt(-n, n) })
    }
    return arrays
}

fun List<IntArray>.serializeTo(path: String) {
    File(path).delete()
    this.forEach { array ->
        File(path).appendText(array.joinToString(postfix = "\n"))
    }
}

fun File.deserializeToIntArrays(): List<IntArray> {
    val arrays = mutableListOf<IntArray>()
    this.forEachLine { line ->
        arrays.add(
            line.split(", ")
                .map(String::toInt)
                .toIntArray()
        )
    }
    return arrays
}
