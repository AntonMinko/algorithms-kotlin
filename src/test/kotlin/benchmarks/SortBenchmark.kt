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
import java.io.File
import java.util.concurrent.TimeUnit

@State(Scope.Benchmark)
@Warmup(iterations = 1)
@Measurement(iterations = 3, time = 1, timeUnit = TimeUnit.SECONDS)
open class SortBenchmark {
    private val path = "./src/test/resources/testArrays.txt"
    private val testSuite = File(path).deserializeToIntArrays().toList()
    private lateinit var testArrays: List<IntArray>

    @Setup(Level.Invocation)
    fun setup() {
        testArrays = testSuite.map { it.clone() }
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
    fun mergeSort() {
        testArrays.forEach { it.mergeSort() }
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
    fun quickSortLumoto() {
        testArrays.forEach { it.quickSort(Strategy.LUMOTO) }
    }

    @Benchmark
    fun quickSortLumotoMedian() {
        testArrays.forEach { it.quickSort(Strategy.LUMOTO_MEDIAN) }
    }

    @Benchmark
    fun quickSortLumotoMedianDuplicates() {
        testArrays.forEach { it.quickSort(Strategy.LUMOTO_MEDIAN_DUPLICATES) }
    }

    @Benchmark
    fun quickSortHoare() {
        testArrays.forEach { it.quickSort(Strategy.HOARE) }
    }

    @Benchmark
    fun kotlinBuiltInSort() {
        testArrays.forEach { it.sort() }
        //testArray.sort()
    }

}