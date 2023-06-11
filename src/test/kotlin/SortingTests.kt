import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.collections.shouldBeSorted
import sorting.bubbleSort
import sorting.selectionSort
import utils.deserializeToIntArrays
import java.io.File

internal class SortingTest : FunSpec({
    val path = "./src/test/resources/testArrays.txt"
    var testArrays: List<IntArray> = emptyList()

    context("Bubble sort") {
        testArrays = File(path).deserializeToIntArrays().take(500).toList()

        testArrays.forEach { arr ->
            test("Bubble sort ${arr.size}") {
                arr.bubbleSort()

                arr.toList().shouldBeSorted()
            }
        }
    }

    context("Selection sort") {
        testArrays = File(path).deserializeToIntArrays().take(500).toList()

        testArrays.forEach { arr ->
            test("Selection sort ${arr.size}") {
                arr.selectionSort()

                arr.toList().shouldBeSorted()
            }
        }
    }

})
