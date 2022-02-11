package de.solugo.rpsgame

import de.solugo.rpsgame.model.Outcome
import de.solugo.rpsgame.model.Player
import de.solugo.rpsgame.model.Round
import de.solugo.rpsgame.model.Shape

object Game {

    fun challenge(player: Shape, opponent: Shape): Outcome = when (player) {
        Shape.Rock -> when (opponent) {
            Shape.Rock -> Outcome.Draw
            Shape.Paper -> Outcome.Loss
            Shape.Scissors -> Outcome.Win
        }
        Shape.Scissors -> when (opponent) {
            Shape.Rock -> Outcome.Loss
            Shape.Paper -> Outcome.Win
            Shape.Scissors -> Outcome.Draw
        }
        Shape.Paper -> when (opponent) {
            Shape.Rock -> Outcome.Win
            Shape.Paper -> Outcome.Draw
            Shape.Scissors -> Outcome.Loss
        }
    }

    fun play(player: Player, opponent: Player, rounds: Int): List<Round> = (0 until rounds).map {
        val playerShape = player.decision()
        val opponentShape = opponent.decision()
        Round(playerShape, opponentShape, challenge(playerShape, opponentShape))
    }

}