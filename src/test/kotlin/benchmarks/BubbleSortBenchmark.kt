package benchmarks

import kotlin.random.Random
import kotlinx.benchmark.Scope
import org.openjdk.jmh.annotations.Benchmark
import org.openjdk.jmh.annotations.Fork
import org.openjdk.jmh.annotations.Measurement
import org.openjdk.jmh.annotations.Setup
import org.openjdk.jmh.annotations.State
import org.openjdk.jmh.annotations.Warmup
import sorting.bubbleSort
import utils.deserializeToIntArrays
import java.io.File
import java.util.concurrent.TimeUnit

@State(Scope.Benchmark)
@Warmup(iterations = 1)
@Measurement(iterations = 10, time = 1, timeUnit = TimeUnit.SECONDS)
open class SortBenchmark {
    private val path = "./src/test/resources/testArrays.txt"
    private lateinit var testArrays: List<IntArray>
    private lateinit var testArray: IntArray

    @Setup
    fun setup() {
        testArrays = File(path).deserializeToIntArrays()
        testArray = arrayOf(7,9,2,1,5,10,8,3,4,6).toIntArray()
    }

    @Benchmark
    fun bubbleSortBenchmark() {
        testArrays.forEach { it.bubbleSort() }
        //testArray.bubbleSort()
    }

    @Benchmark
    fun kotlinSortBenchmark() {
        testArrays.forEach { it.sort() }
        //testArray.sort()
    }

}