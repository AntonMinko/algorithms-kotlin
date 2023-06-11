package benchmarks

import kotlinx.benchmark.Scope
import org.openjdk.jmh.annotations.Benchmark
import org.openjdk.jmh.annotations.Level
import org.openjdk.jmh.annotations.Measurement
import org.openjdk.jmh.annotations.Setup
import org.openjdk.jmh.annotations.State
import org.openjdk.jmh.annotations.Warmup
import sorting.bubbleSort
import sorting.selectionSort
import utils.deserializeToIntArrays
import java.io.File
import java.util.concurrent.TimeUnit

@State(Scope.Benchmark)
@Warmup(iterations = 1)
@Measurement(iterations = 2, time = 1, timeUnit = TimeUnit.SECONDS)
open class SortBenchmark {
    private val path = "./src/test/resources/testArrays.txt"
    private val testSuite = File(path).deserializeToIntArrays().toList()
    private lateinit var testArrays: List<IntArray>
    //private lateinit var testArray: IntArray

    @Setup(Level.Invocation)
    fun setup() {
        testArrays = testSuite.map { it.clone() }
        //testArray = arrayOf(7,9,2,1,5,10,8,3,4,6).toIntArray()
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
    fun kotlinSortBenchmark() {
        testArrays.forEach { it.sort() }
        //testArray.sort()
    }

}