import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.collections.shouldBeSorted
import sorting.bubbleSort
import utils.deserializeToIntArrays
import java.io.File

internal class SortingTest : FunSpec({
    val path = "./src/test/resources/testArrays.txt"
    val testArrays = File(path).deserializeToIntArrays()

    testArrays.forEach { arr ->
        test("BubbleSort sorting ${arr.size}") {
            arr.bubbleSort()

            arr.toList().shouldBeSorted()
        }
    }
})
