package structural

interface Component{
    fun operation()
}

interface Leaf: Component

class LeafImpl: Leaf {
    override fun operation() {
        println("working")
    }
}

class Composite: Component{
    private val leafs = mutableListOf<Leaf>()

    fun addLeaf(leaf: Leaf){
        leafs.add(leaf)
    }

    fun removeLeaf(leaf: Leaf){
        leafs.add(leaf)
    }

    override fun operation() {
        leafs.forEach { it.operation() }
    }
}

fun main(){
    val composite = Composite()
    val leafA = LeafImpl()
    val leafB = LeafImpl()
    composite.addLeaf(leafA)
    composite.addLeaf(leafB)
    composite.operation()
}