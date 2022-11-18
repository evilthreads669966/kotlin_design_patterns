
# Design Patterns
### Chain of Responsibility
```kotlin
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
```

### Command
```kotlin
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
```

### Interpreter
```kotlin
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
```

### Iterator
```kotlin
class Family: Iterable<String>{
    private val people = listOf<String>("Chris", "Michael", "Greg")

    override fun iterator() = people.iterator()
}
```

### Builder
```kotlin
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
```

### State
```kotlin
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
```

### Strategy
```kotlin
interface BillingStrategy{
    fun getPrice(rawPrice: Double): Double
}

class CustomerBill(val billingStrategy: BillingStrategy){
    private val drinks = mutableListOf<Double>()

    fun addDrink(drink: String){
        drinks.add(billingStrategy.getPrice(6.50))
    }

    fun getTotal() = drinks.sum()

}

class HappyHourStrategy: BillingStrategy {
    override fun getPrice(rawPrice: Double): Double = rawPrice / 2
}

class NormalStrategy: BillingStrategy{
    override fun getPrice(rawPrice: Double): Double = rawPrice
}

class FreeTabStrategy: BillingStrategy{
    override fun getPrice(rawPrice: Double): Double = 0.0
}
```

### Template Method
```kotlin
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
```

### Visitor
```kotlin
interface Story{
    val title: String
    val body: String
    fun accept(visitor: ShareStoryVisitor)
}

interface ShareStoryVisitor{
    fun visit(story: SimpleStory)
}

class SimpleStory(override val title: String, override val body: String) : Story {
    override fun accept(visitor: ShareStoryVisitor) {
        visitor.visit(this)
    }
}

class EmailVisitor: ShareStoryVisitor {
    override fun visit(story: SimpleStory) {
        println("emailing story\n${story.title}\n${story.body}")
    }
}

class SmsVisitor: ShareStoryVisitor {
    override fun visit(story: SimpleStory) {
        println("texting story\n${story.title}\n${story.body}")
    }
}

class FacebookVisitor: ShareStoryVisitor{
    override fun visit(story: SimpleStory) {
        println("posting to facebook story\n${story.title}\n${story.body}")
    }
}

class TwitterVisitor: ShareStoryVisitor{
    override fun visit(story: SimpleStory) {
        println("tweeting story\n${story.title}\n${story.body}")
    }
}
```

### Abstract Factory
```kotlin
interface FruitFactory{
    fun create(): Fruit
}

interface Fruit

class Apple private constructor() : Fruit{
    companion object Factory: FruitFactory{
        override fun create(): Fruit = Apple()
    }
}

class Orange private constructor(): Fruit{
    companion object Factory: FruitFactory {
        override fun create(): Fruit = Orange()
    }
}
```

### Builder
```kotlin
data class Building private constructor(val width: Double, val height: Double){
    class Builder{
        private var width: Double = 0.0
        private var height: Double = 0.0

        fun setWidth(width: Double) = this.apply { this.width = width }

        fun setHeight(height: Double) = this.apply { this.height = height }

        fun build() = Building(this.width, this.height)
    }
}
```

### Factory Method
```kotlin
interface Person{
    fun getName(): String
}

class Villager: Person{
    override fun getName(): String = "villager"
}

class CityPerson: Person {
    override fun getName(): String = "city person"
}

enum class PersonType{
    RURAL, URBAN,
}

object PersonFactory{
    fun makePerson(type: PersonType): Person{
        return when(type){
            PersonType.RURAL -> Villager()
            PersonType.URBAN -> CityPerson()
        }
    }
}
```

### Observer
```kotlin
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
```

### Prototype
```kotlin
data class Something<T>(var state: T)

fun main(){
    val something = Something("Chris")
    val prototype = something.copy()
}
```

### Singleton
```kotlin
object Singleton
```

### Adapter
```kotlin
interface LightningPhone{
    fun recharge()
    fun useLightning()
}

interface MicroUsbPhone{
    fun recharge()
    fun useMicroUsb()
}

class IPhone: LightningPhone {
    private var connected = false

    override fun recharge() {
        if(connected)
            println("recharging")
        else
            println("connect lightning first")
    }

    override fun useLightning() {
        connected = true
        println("lightning connected")
    }
}

class Android: MicroUsbPhone {
    private var connected = false

    override fun recharge() {
        if(connected)
            println("charging")
        else
            println("connect micro usb first")
    }

    override fun useMicroUsb() {
        connected = true
        println("micro usb connected")
    }
}

class LightningToMicroUSbAdapter(private val lightningPhone: LightningPhone): MicroUsbPhone {
    override fun recharge() {
        lightningPhone.recharge()
    }

    override fun useMicroUsb() {
        println("micro usb connected")
        lightningPhone.useLightning()
    }
}
```

### Bridge
```kotlin
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

```

### Composite
```kotlin
interface Component{
    fun operation()
}

interface Leaf: Component

class LeafImpl: Leaf {
    override fun operation() {
        println("working")
    }
}

class Composite: Component{
    private val leafs = mutableListOf<Leaf>()

    fun addLeaf(leaf: Leaf){
        leafs.add(leaf)
    }

    fun removeLeaf(leaf: Leaf){
        leafs.add(leaf)
    }

    override fun operation() {
        leafs.forEach { it.operation() }
    }
}
```

### Decorator
```kotlin
interface Bike{
    fun getCost(): Double
}

class AluminumBike: Bike {
    override fun getCost(): Double = 500.0
}

abstract class BikeDecorator(private val bike: Bike): Bike{
    override fun getCost(): Double = bike.getCost()
}

class LightsDecorator(bike: Bike): BikeDecorator(bike) {
    override fun getCost(): Double = super.getCost() + 40.0
}
```

### Facade
```kotlin
class BluetoothPairing{

    fun checkIfPaired() = true

    fun checkForPairables(){
        println("searching for other bluetooth devices")
    }

    fun pair(){
        println("pairing")
    }
}

class BluetoothManager{
    fun pair(){
        val pairing = BluetoothPairing()
        pairing.checkIfPaired()
        pairing.checkForPairables()
        pairing.pair()
    }
}
```

### Flyweight
```kotlin
interface AuraxisPlayer

class VanuPlayer: AuraxisPlayer

class TerranRepublicPlayer: AuraxisPlayer

class NewConglomeratePlayer: AuraxisPlayer

enum class AuraxisFactions{
    VANU_SOVEREIGNTY, TERRAN_REPUBLIC, NEW_CONGLOMERATE
}

class WarpGate{
    private val players = hashMapOf<AuraxisFactions, AuraxisPlayer>()

    fun spawn(faction: AuraxisFactions): AuraxisPlayer {
        if(players.containsKey(faction))
            return players[faction]!!
        return when(faction){
            AuraxisFactions.NEW_CONGLOMERATE -> {
                val player = NewConglomeratePlayer()
                players.put(faction, player)
                player
            }
            AuraxisFactions.TERRAN_REPUBLIC -> {
                val player = TerranRepublicPlayer()
                players.put(faction, player)
                player
            }
            AuraxisFactions.VANU_SOVEREIGNTY -> {
                val player = VanuPlayer()
                players.put(faction, player)
                player
            }
        }
    }
}
```

### Protection Proxy
```kotlin
interface Car{
    fun drive()
}

class Corvette: Car{
    override fun drive() {
        println("Driving corvette!")
    }
}

class ProxyCorvette(private val driver: Driver): Car{
    private val car = Corvette()

    override fun drive() {
        if(driver.age > 15)
            car.drive()
        else
            println("driver not old enough")
    }
}

class Driver(val age: Int)
```

### Repository
```kotlin
interface Person

class Chris: Person

class Thomas: Person

interface Repository<T>{
    val items: List<T>

    fun getItem(index: Int): T?
}

class Repo<T>(override val items: List<T>) : Repository<T>{
    override fun getItem(index: Int): T? = items.getOrNull(index)
}
```

### Servant
```kotlin
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
```
