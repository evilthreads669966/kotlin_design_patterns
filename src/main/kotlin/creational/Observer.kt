package creational

typealias Observer = (String) -> Unit

class EventSource{
    private val observers = mutableListOf<Observer>()

    fun addObserver(o: Observer) = observers.add(o)

    private fun notifyObservers(event: String) = observers.forEach { it(event) }

    fun scanSystemIn(){
        while(true){
            val event = readLine()
            if(event != null)
                notifyObservers(event)
        }
    }
}

fun main(){
    val eventSource = EventSource()
    eventSource.addObserver { println("$it event is received") }
    eventSource.addObserver { println("$it is received by other observer") }
    eventSource.scanSystemIn()
}