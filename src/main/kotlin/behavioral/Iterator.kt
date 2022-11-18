package behavioral

class Family: Iterable<String>{
    private val people = listOf<String>("Chris", "Michael", "Greg")

    override fun iterator() = people.iterator()
}

fun main(){
    val family = Family()
    for(person in family)
        println(person)
    family.forEach { println(it) }
}