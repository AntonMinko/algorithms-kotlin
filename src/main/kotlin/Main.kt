import utils.deserializeToIntArrays
import utils.generateArrays
import utils.serializeTo
import java.io.File

fun main(args: Array<String>) {
    val path = "./src/test/resources/testArrays.txt"
    generateArrays(1, 10_000, 5).serializeTo(path)
    val arrays = File(path).deserializeToIntArrays().toList()
}