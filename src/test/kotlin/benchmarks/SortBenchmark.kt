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
@Measurement(iterations = 5, time = 1, timeUnit = TimeUnit.SECONDS)
open class SortBenchmark {
    private val path = "./src/test/resources/testArrays.txt"
    private val testSuite = File(path).deserializeToIntArrays().toList()
    private lateinit var testArrays: List<IntArray>

    @Setup(Level.Invocation)
    fun setup() {
        testArrays = testSuite.map { it.clone() }
    }

    @Benchmark
    fun bubbleSortBenchmark() {
        testArrays.forEach { it.bubbleSort() }
    }

    @Benchmark
    fun selectionSortBenchmark() {
        testArrays.forEach { it.selectionSort() }
    }

    @Benchmark
    fun insertionSortBenchmark() {
        testArrays.forEach { it.insertionSort() }
    }

    @Benchmark
    fun mergeSortBenchmark() {
        testArrays.forEach { it.mergeSort() }
    }

    @Benchmark
    fun radixSortBenchmark10() {
        testArrays.forEach { it.radixSort(base = 10) }
    }

    @Benchmark
    fun radixSortBenchmark16() {
        testArrays.forEach { it.radixSort(base = 16) }
    }


    @Benchmark
    fun radixSortBenchmark256() {
        testArrays.forEach { it.radixSort(base = 256) }
    }


    @Benchmark
    fun kotlinSortBenchmark() {
        testArrays.forEach { it.sort() }
        //testArray.sort()
    }

}