package behavioral

interface BillingStrategy{
    fun getPrice(rawPrice: Double): Double
}

class CustomerBill(val billingStrategy: BillingStrategy){
    private val drinks = mutableListOf<Double>()

    fun addDrink(drink: String){
        drinks.add(billingStrategy.getPrice(6.50))
    }

    fun getTotal() = drinks.sum()

}

class HappyHourStrategy: BillingStrategy {
    override fun getPrice(rawPrice: Double): Double = rawPrice / 2
}

class NormalStrategy: BillingStrategy{
    override fun getPrice(rawPrice: Double): Double = rawPrice
}

class FreeTabStrategy: BillingStrategy{
    override fun getPrice(rawPrice: Double): Double = 0.0
}

fun main(){
    val bill = CustomerBill(HappyHourStrategy())
    bill.addDrink("vodka")
    bill.addDrink("whiskey")
    val total = bill.getTotal()
    require(total == 6.50)
}