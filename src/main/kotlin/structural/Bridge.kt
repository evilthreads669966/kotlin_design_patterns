interface Logger{
    companion object Factory{
        fun warn(): Logger = WarnLogger()
        fun info(): Logger = InfoLogger()
    }
    fun log(message: String)
}

class WarnLogger: Logger{
    override fun log(message: String) = println("WARN: $message")
}

class InfoLogger: Logger {
    override fun log(message: String) = println("INFO: $message")
}

abstract class Account{
    private var logger = Logger.info()

    fun setLogger(logger: Logger){
        this.logger = logger
    }

    fun log(message: String) = logger.log(message)

    abstract fun withdraw(amount: Double)
}

class BankAccount(private var balance: Double): Account(){
    override fun withdraw(amount: Double){
        if(amount <= balance){
            log("Withdrawing $amount from account with $balance")
            balance -= amount
            log("new balance is $balance")
        }
        else{
            setLogger(Logger.warn())
            log("balance of $balance is too low for withdrawal of $amount")
        }
    }
}

fun main(){
    val account = BankAccount(300.23)
    account.withdraw(50.00)
    account.withdraw(300.0)
}