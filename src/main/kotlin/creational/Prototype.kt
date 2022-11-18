package creational

data class Something<T>(var state: T)

fun main(){
    val something = Something("Chris")
    val prototype = something.copy()
}