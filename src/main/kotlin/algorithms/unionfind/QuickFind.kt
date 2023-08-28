package algorithms.unionfind

class QuickFind(n: Int) {
    private val components = IntArray(n) { it }

    var count = n
        private set

    fun find(p: Int): Int = components[p]

    fun union(p: Int, q: Int) {
        val pComponent = find(p)
        val qComponent = find(q)

        if (pComponent == qComponent) return

        for(i in components.indices) {
            if (components[i] == qComponent) {
                components[i] = pComponent
            }
        }

        count--
    }

    fun connected(p: Int, q: Int) = find(p) == find(q)
}