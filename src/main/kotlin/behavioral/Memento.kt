package behavioral

class Memento<T>(val state: T)

class Originator<T>(var state: T){

    fun createMemento(): Memento<T> = Memento(state)

    fun restoreMemento(memento: Memento<T>){
        this.state = memento.state
    }
}

class Caretaker<T>{
    val mementos = mutableListOf<Memento<T>>()

    fun save(memento: Memento<T>){
        mementos.add(memento)
    }

    fun restore(index: Int): Memento<T>{
        return mementos[index]
    }
}

fun main(){
    val originator = Originator<String>("Chris")
    val caretaker = Caretaker<String>()
    var memento = originator.createMemento()
    caretaker.save(memento)
    originator.state = "Thomas"
    memento = originator.createMemento()
    caretaker.save(memento)
    memento = caretaker.restore(0)
    originator.restoreMemento(memento)
    require(originator.state == "Chris")
}