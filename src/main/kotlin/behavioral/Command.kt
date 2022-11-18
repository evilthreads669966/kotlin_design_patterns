package behavioral

interface Command{
    fun execute()
}

interface Switchable{
    fun powerOn()
    fun powerOff()
}

class Light: Switchable {
    override fun powerOn() = println("the light is on")

    override fun powerOff() = println("the light is off")
}

class CloseSwitchCommand(private val switchable: Switchable): Command{
    override fun execute() {
        switchable.powerOff()
    }
}

class OpenSwitchCommand(private val switchable: Switchable): Command {
    override fun execute() {
        switchable.powerOn()
    }
}

class Switch(private val close: Command, private val open: Command){
    fun open(){
        open.execute()
    }
    fun close(){
        close.execute()
    }
}

fun main() {
    val lamp = Light()
    val open = OpenSwitchCommand(lamp)
    val close = CloseSwitchCommand(lamp)
    val switch = Switch(close, open)
    switch.open()
}