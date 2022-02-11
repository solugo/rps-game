@file:JvmName("RpsGame")

import de.solugo.rpsgame.Game
import de.solugo.rpsgame.model.Player
import de.solugo.rpsgame.model.PlayerStrategy
import de.solugo.rpsgame.model.Shape
import kotlinx.cli.ArgParser
import kotlinx.cli.ArgType
import kotlinx.cli.default

fun main(args: Array<String>) {

    val parser = ArgParser("rps-game")

    val count by parser.option(
        type = ArgType.Int,
        shortName = "r",
        fullName = "rounds",
        description = "number of rounds played"
    ).default(100)

    val playerStrategy by parser.option(
        type = ArgType.Choice<Strategy>(),
        shortName = "ps",
        fullName = "player-strategy",
        description = "the players decision strategy"
    ).default(Strategy.RANDOM)

    val opponentStrategy by parser.option(
        type = ArgType.Choice<Strategy>(),
        shortName = "os",
        fullName = "opponent-strategy",
        description = "the opponents decision strategy"
    ).default(Strategy.ROCK)

    parser.parse(args)

    val player = Player(playerStrategy.strategy)
    val opponent = Player(opponentStrategy.strategy)
    val rounds = Game.playRounds(player, opponent, count)
    val statistics = Game.calculateStatistics(rounds)

    println(
        """
        |In a game of $count rounds
        |- the outcome was: ${statistics.outcomes.entries.sortedBy { it.key }.stats()}
        |- the player chose: ${statistics.playerDecisions.entries.sortedBy { it.key }.stats()}
        |- the opponent chose: ${statistics.opponentDecisions.entries.sortedBy { it.key }.stats()}
        """.trimMargin()
    )

}

enum class Strategy(val strategy: PlayerStrategy) {
    RANDOM(PlayerStrategy.Random),
    ROCK(PlayerStrategy.Constant(Shape.Rock)),
    PAPER(PlayerStrategy.Constant(Shape.Paper)),
    SCISSORS(PlayerStrategy.Constant(Shape.Scissors)),
}

fun List<Map.Entry<Any, Int>>.stats() = joinToString { "${it.key} ${it.value} times" }