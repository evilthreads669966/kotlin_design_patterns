package structural

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

fun main() {
    val mgr = BluetoothManager()
    mgr.pair()
}