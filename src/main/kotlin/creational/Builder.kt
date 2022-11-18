package creational

data class Building private constructor(val width: Double, val height: Double){
    class Builder{
        private var width: Double = 0.0
        private var height: Double = 0.0

        fun setWidth(width: Double) = this.apply { this.width = width }

        fun setHeight(height: Double) = this.apply { this.height = height }

        fun build() = Building(this.width, this.height)
    }
}

fun main(){
    val builder = Building.Builder()
    val building = builder.setHeight(24.0).setWidth(8.0).build()
    println(building)
}