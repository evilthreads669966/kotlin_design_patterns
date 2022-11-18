package behavioral

sealed class Expression{
    class Constant(val value: Int): Expression()
    class Add(val left: Expression, val right: Expression): Expression()
    class Subtract(val left: Expression, val right: Expression): Expression()
}

class Interpreter{
    fun interpret(expression: Expression): Int{
        return when(expression){
            is Expression.Constant -> expression.value
            is Expression.Add -> interpret(expression.left) + interpret(expression.right)
            is Expression.Subtract -> interpret(expression.left) - interpret(expression.right)
        }
    }
}

fun main(){
    val one = Expression.Constant(1)
    val two = Expression.Constant(2)
    val interpreter = Interpreter()
    val addition = Expression.Add(one, two)
    val result = interpreter.interpret(addition)
    require(result == 3)
}