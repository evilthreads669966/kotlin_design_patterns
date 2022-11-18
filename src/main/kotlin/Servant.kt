interface Servant<T>{
    fun serve(serviced: Serviced<T>, state: T)
}

interface Serviced<T>{
    var state: T
    fun operation(state: T)
}

class ServantImpl<T>: Servant<T> {
    override fun serve(serviced: Serviced<T>, state: T) {
        serviced.operation(state)
    }
}

class ServicedImpl<T>(override var state: T) : Serviced<T> {
    override fun operation(state: T) {
        this.state = state
    }
}

fun main(){
    val servant = ServantImpl<String>()
    val serviced = ServicedImpl<String>("Chris")
    servant.serve(serviced, "Thomas")
}