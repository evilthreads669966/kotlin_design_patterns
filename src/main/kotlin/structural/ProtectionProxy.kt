package structural

interface Car{
    fun drive()
}

class Corvette: Car{
    override fun drive() {
        println("Driving corvette!")
    }
}

class ProxyCorvette(private val driver: Driver): Car{
    private val car = Corvette()

    override fun drive() {
        if(driver.age > 15)
            car.drive()
        else
            println("driver not old enough")
    }
}

class Driver(val age: Int)