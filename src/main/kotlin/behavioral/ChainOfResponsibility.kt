abstract class PipelineStep{
    private var next: PipelineStep? = null

    fun nextStep(step: PipelineStep){
        next = step
    }

    fun receiveRequest(request: String){
        handleRequest(request)
        next?.receiveRequest(request)
    }

    abstract fun handleRequest(request: String)
}

class StepA: PipelineStep() {
    override fun handleRequest(request: String) {
        println("Step A")
        println(request)
    }
}

class StepB: PipelineStep() {
    override fun handleRequest(request: String) {
        println("Step B")
        println(request)
    }
}

class StepC: PipelineStep() {
    override fun handleRequest(request: String) {
        println("Step C")
        println(request)
    }
}

fun main(){
    val stepA = StepA()
    val stepB = StepB()
    val stepC = StepC()

    stepA.nextStep(stepB)
    stepB.nextStep(stepC)

    stepA.receiveRequest("Hello world!")
}