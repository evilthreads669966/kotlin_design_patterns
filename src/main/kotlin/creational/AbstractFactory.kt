package creational

interface FruitFactory{
    fun create(): Fruit
}

interface Fruit

class Apple private constructor() : Fruit{
    companion object Factory: FruitFactory{
        override fun create(): Fruit = Apple()
    }
}

class Orange private constructor(): Fruit{
    companion object Factory: FruitFactory {
        override fun create(): Fruit = Orange()
    }
}

fun main(){
    val apple = Apple.create()
    val orange = Orange.create()
}