package structural

interface Bike{
    fun getCost(): Double
}

class AluminumBike: Bike {
    override fun getCost(): Double = 500.0
}

abstract class BikeDecorator(private val bike: Bike): Bike{
    override fun getCost(): Double = bike.getCost()
}

class LightsDecorator(bike: Bike): BikeDecorator(bike) {
    override fun getCost(): Double = super.getCost() + 40.0
}

fun main(){
    val bikeWithLights = LightsDecorator(AluminumBike())
    require(bikeWithLights.getCost() == 540.0)
}