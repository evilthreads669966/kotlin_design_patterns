interface Person

class Chris: Person

class Thomas: Person

interface Repository<T>{
    val items: List<T>

    fun getItem(index: Int): T?
}

class Repo<T>(override val items: List<T>) : Repository<T>{
    override fun getItem(index: Int): T? = items.getOrNull(index)
}

fun main() {
    val repo = Repo(listOf(Chris(), Thomas()))
    val chris = repo.getItem(0)
    println(chris)
}