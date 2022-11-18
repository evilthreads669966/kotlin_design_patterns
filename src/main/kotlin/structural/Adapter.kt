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

fun main(){
    val iphone = IPhone()
    val android = Android()

    val adapter = LightningToMicroUSbAdapter(iphone)
    adapter.useMicroUsb()
    adapter.recharge()
}