package de.solugo.rpsgame

import de.solugo.rpsgame.model.*

object Game {

    fun decideOutcome(playerShape: Shape, opponentShape: Shape): Outcome = when (playerShape) {
        Shape.Rock -> when (opponentShape) {
            Shape.Rock -> Outcome.Draw
            Shape.Paper -> Outcome.Loss
            Shape.Scissors -> Outcome.Win
        }
        Shape.Scissors -> when (opponentShape) {
            Shape.Rock -> Outcome.Loss
            Shape.Paper -> Outcome.Win
            Shape.Scissors -> Outcome.Draw
        }
        Shape.Paper -> when (opponentShape) {
            Shape.Rock -> Outcome.Win
            Shape.Paper -> Outcome.Draw
            Shape.Scissors -> Outcome.Loss
        }
    }

    fun playRounds(player: Player, opponent: Player, rounds: Int): List<Round> = (0 until rounds).map {
        val playerShape = player.strategy.decide()
        val opponentShape = opponent.strategy.decide()
        Round(playerShape, opponentShape, decideOutcome(playerShape, opponentShape))
    }

    fun calculateStatistics(rounds: Collection<Round>): Statistics = Statistics(
        outcomes = rounds.groupBy(Round::outcome).mapValues { it.value.count() },
        playerDecisions = rounds.groupBy(Round::player).mapValues { it.value.count() },
        opponentDecisions = rounds.groupBy(Round::opponent).mapValues { it.value.count() },
    )

}