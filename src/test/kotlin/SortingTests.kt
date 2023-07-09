import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.collections.shouldBeSorted
import sorting.*
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

    context("Insertion sort") {
        testArrays = File(path).deserializeToIntArrays().take(500).toList()

        testArrays.forEach { arr ->
            test("Insertion sort ${arr.size}") {
                arr.insertionSort()

                arr.toList().shouldBeSorted()
            }
        }
    }

    context("Merge sort") {
        testArrays = File(path).deserializeToIntArrays().take(500).toList()

        testArrays.forEach { arr ->
            test("Merge sort ${arr.size}") {
                arr.mergeSort()

                arr.toList().shouldBeSorted()
            }
        }
    }

    context("Radix sort") {
        beforeContainer {
            testArrays = File(path).deserializeToIntArrays().take(500).toList()
        }

        context("Base 10") {
            testArrays.forEach { arr ->
                test("Radix sort ${arr.size}") {
                    arr.radixSort(base = 10)

                    arr.toList().shouldBeSorted()
                }
            }
        }

        context("Base 16") {
            testArrays.forEach { arr ->
                test("Radix sort ${arr.size}") {
                    arr.radixSort(base = 16)

                    arr.toList().shouldBeSorted()
                }
            }
        }

        context("Base 256") {
            testArrays.forEach { arr ->
                test("Radix sort ${arr.size}") {
                    arr.radixSort(base = 256)

                    arr.toList().shouldBeSorted()
                }
            }
        }
    }

})
