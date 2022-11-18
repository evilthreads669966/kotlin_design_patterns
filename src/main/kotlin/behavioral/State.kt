package behavioral

sealed class State{
    object Enabled: State()
    object Disabled: State()
}

class LightSwitch{
    private var state: State = State.Disabled
    val isOn: Boolean
        get() = state is State.Enabled

    fun turn(){
        if(state is State.Enabled)
            state = State.Disabled
        else
            state = State.Enabled
    }
}

fun main(){
    val switch = LightSwitch()
    switch.turn()
    require(switch.isOn)
    switch.turn()
    require(!switch.isOn)
}