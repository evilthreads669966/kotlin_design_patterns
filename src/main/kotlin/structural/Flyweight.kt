package structural

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

fun main(){
    val warpgate = WarpGate()
    val ncPlayer = warpgate.spawn(AuraxisFactions.NEW_CONGLOMERATE)
    require(ncPlayer is NewConglomeratePlayer)
}