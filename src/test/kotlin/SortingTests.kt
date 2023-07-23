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

    context("Quick sort") {
        beforeContainer {
            testArrays = File(path).deserializeToIntArrays().take(500).toList()
        }

        context("Extra memory") {
            testArrays.forEach { arr ->
                test("Quick sort. Extra memory ${arr.size}") {
                    arr.quickSort(Strategy.EXTERNAL)

                    arr.toList().shouldBeSorted()
                }
            }
        }

        context("Extra memory Efficient") {
            testArrays.forEach { arr ->
                test("Quick sort. Extra memory efficient ${arr.size}") {
                    arr.quickSort(Strategy.EXTERNAL_EFFICIENT)

                    arr.toList().shouldBeSorted()
                }
            }
        }

        context("Lumoto") {
            testArrays.forEach { arr ->
                test("Quick sort. Lumoto ${arr.size}") {
                    arr.quickSort(Strategy.LUMOTO)

                    arr.toList().shouldBeSorted()
                }
            }
        }

        context("Lumoto with median pivot") {
            testArrays.forEach { arr ->
                test("Quick sort. Lumoto with median pivot ${arr.size}") {
                    arr.quickSort(Strategy.LUMOTO_MEDIAN)

                    arr.toList().shouldBeSorted()
                }
            }
        }

        context("Lumoto with insertion sort") {
            testArrays.forEach { arr ->
                test("Quick sort. Lumoto with insertion sort ${arr.size}") {
                    arr.quickSort(Strategy.LUMOTO_INSERTION)

                    arr.toList().shouldBeSorted()
                }
            }
        }

        context("Lumoto with median pivot point and duplicates") {
            testArrays.forEach { arr ->
                test("Quick sort. Lumoto with median pivot and duplicates ${arr.size}") {
                    arr.quickSort(Strategy.LUMOTO_MEDIAN_DUPLICATES)

                    arr.toList().shouldBeSorted()
                }
            }
        }

        context("Hoare") {
            testArrays.forEach { arr ->
                test("Quick sort. Hoare ${arr.size}") {
                    arr.quickSort(Strategy.HOARE)

                    arr.toList().shouldBeSorted()
                }
            }
        }
    }
})
