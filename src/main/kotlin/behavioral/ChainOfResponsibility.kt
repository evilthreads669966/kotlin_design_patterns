abstract class PipelineStep<T>{
    private var next: PipelineStep<T>? = null

    fun nextStep(step: PipelineStep<T>){
        next = step
    }

    fun receiveRequest(request: T){
        handleRequest(request)
        next?.receiveRequest(request)
    }

    abstract fun handleRequest(request: T)
}

class StepA<T>: PipelineStep<T>() {
    override fun handleRequest(request: T) {
        println("Step A")
        println(request)
    }
}

class StepB<T>: PipelineStep<T>() {
    override fun handleRequest(request: T) {
        println("Step B")
        println(request)
    }
}

class StepC<T>: PipelineStep<T>() {
    override fun handleRequest(request: T) {
        println("Step C")
        println(request)
    }
}

fun main(){
    val stepA = StepA<String>()
    val stepB = StepB<String>()
    val stepC = StepC<String>()

    stepA.nextStep(stepB)
    stepB.nextStep(stepC)

    stepA.receiveRequest("Hello world!")
}