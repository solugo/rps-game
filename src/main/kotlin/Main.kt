@file:JvmName("RpsGame")

import de.solugo.rpsgame.Game
import de.solugo.rpsgame.model.Player
import de.solugo.rpsgame.model.PlayerStrategy
import de.solugo.rpsgame.model.Shape

fun main() {

    val count = 100
    val player = Player(PlayerStrategy.Random)
    val opponent = Player(PlayerStrategy.Constant(Shape.Rock))
    val rounds = Game.playRounds(player, opponent, count)
    val statistics = Game.calculateStatistics(rounds)

    fun List<Map.Entry<Any, Int>>.stats() = joinToString { "${it.key} ${it.value} times" }

    println(
        """
        |In a game of $count rounds
        |- the outcome was: ${statistics.outcomes.entries.sortedBy { it.key }.stats()}
        |- the player chose: ${statistics.playerDecisions.entries.sortedBy { it.key }.stats() }
        |- the opponent chose: ${statistics.opponentDecisions.entries.sortedBy { it.key }.stats() }
        """.trimMargin()
    )

}

