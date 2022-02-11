package de.solugo.rpsgame.model

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

}