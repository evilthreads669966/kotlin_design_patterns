package creational

interface Person{
    fun getName(): String
}

class Villager: Person{
    override fun getName(): String = "villager"
}

class CityPerson: Person {
    override fun getName(): String = "city person"
}

enum class PersonType{
    RURAL, URBAN,
}

object PersonFactory{
    fun makePerson(type: PersonType): Person{
        return when(type){
            PersonType.RURAL -> Villager()
            PersonType.URBAN -> CityPerson()
        }
    }
}

fun main(){
    val villager = PersonFactory.makePerson(PersonType.RURAL)
    require(villager is Villager)
}