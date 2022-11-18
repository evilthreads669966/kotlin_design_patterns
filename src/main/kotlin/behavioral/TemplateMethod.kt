package behavioral

interface Game{
    fun initialize()
    fun startPlaying()
    fun endPlaying()

    fun play(){
        initialize()
        startPlaying()
        endPlaying()
    }
}

class SuperMario: Game {
    override fun initialize() {
        println("loading mario")
    }

    override fun startPlaying() {
        println("displaying mario start screen")
    }

    override fun endPlaying() {
        println("shutting down mario")
    }
}

fun main(){
    val mario = SuperMario()
    mario.play()
}