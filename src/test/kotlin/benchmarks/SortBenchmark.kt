package benchmarks

import kotlinx.benchmark.Scope
import org.openjdk.jmh.annotations.Benchmark
import org.openjdk.jmh.annotations.Level
import org.openjdk.jmh.annotations.Measurement
import org.openjdk.jmh.annotations.Setup
import org.openjdk.jmh.annotations.State
import org.openjdk.jmh.annotations.Warmup
import sorting.*
import utils.deserializeToIntArrays
import utils.generateArray
import utils.swap
import java.io.File
import java.util.concurrent.TimeUnit
import kotlin.random.Random

@State(Scope.Benchmark)
@Warmup(iterations = 1)
@Measurement(iterations = 3, time = 1, timeUnit = TimeUnit.SECONDS)
open class SortBenchmark {
    private val path = "./src/test/resources/testArrays.txt"
    private val testSuite = File(path).deserializeToIntArrays().toList()
    private lateinit var testArrays: List<IntArray>

    @Setup(Level.Invocation)
    fun setup() {
        //testArrays = testSuite.map { it.clone() }
        val size = 6_400_000
        testArrays = (1..1).map { generateArray(size, 0, size * 2)
//            .sortedArray()
//            .also { arr ->
//                (1..100).forEach {
//                    val i = Random.nextInt(0, size-1)
//                    val j = Random.nextInt(0, size-1)
//                    arr.swap(i, j)
//                }
//            }
        }
    }

    @Benchmark
    fun bubbleSort() {
        testArrays.forEach { it.bubbleSort() }
    }

    @Benchmark
    fun selectionSort() {
        testArrays.forEach { it.selectionSort() }
    }

    @Benchmark
    fun insertionSort() {
        testArrays.forEach { it.insertionSort() }
    }

    @Benchmark
    fun insertionSortOptimized() {
        testArrays.forEach { it.insertionSortOptimized() }
    }

    @Benchmark
    fun mergeSortBasic() {
        testArrays.forEach { it.mergeSortBasic() }
    }


    @Benchmark
    fun mergeSortTopDown() {
        testArrays.forEach { it.mergeSortTopDown() }
    }

    @Benchmark
    fun mergeSortBottomUp() {
        testArrays.forEach { it.mergeSortBottomUp() }
    }

    @Benchmark
    fun radixSort10() {
        testArrays.forEach { it.radixSort(base = 10) }
    }

    @Benchmark
    fun radixSort16() {
        testArrays.forEach { it.radixSort(base = 16) }
    }


    @Benchmark
    fun radixSort256() {
        testArrays.forEach { it.radixSort(base = 256) }
    }

    @Benchmark
    fun quickSortExternal() {
        testArrays.forEach { it.quickSort(Strategy.EXTERNAL) }
    }

    @Benchmark
    fun quickSortExternalEfficient() {
        testArrays.forEach { it.quickSort(Strategy.EXTERNAL_EFFICIENT) }
    }

    @Benchmark
    fun quickSortLumoto() {
        testArrays.forEach { it.quickSort(Strategy.LUMOTO) }
    }

    @Benchmark
    fun quickSortLumotoMedian() {
        testArrays.forEach { it.quickSort(Strategy.LUMOTO_MEDIAN) }
    }

    @Benchmark
    fun quickSortLumotoInsertion() {
        testArrays.forEach { it.quickSort(Strategy.LUMOTO_INSERTION) }
    }

    @Benchmark
    fun quickSortHoare() {
        testArrays.forEach { it.quickSort(Strategy.HOARE) }
    }

    @Benchmark
    fun kotlinBuiltInSort() {
        testArrays.forEach { it.sort() }
    }
}