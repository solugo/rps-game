package de.solugo.rpsgame

import de.solugo.rpsgame.model.Game
import de.solugo.rpsgame.model.Outcome
import de.solugo.rpsgame.model.Shape
import org.junit.jupiter.api.Test
import strikt.api.expect
import strikt.assertions.isEqualTo

class GameTest {

    companion object {
        private val outcomes = hashMapOf(
            (Shape.Rock to Shape.Rock) to Outcome.Draw,
            (Shape.Rock to Shape.Paper) to Outcome.Loss,
            (Shape.Rock to Shape.Scissors) to Outcome.Win,
            (Shape.Paper to Shape.Rock) to Outcome.Win,
            (Shape.Paper to Shape.Paper) to Outcome.Draw,
            (Shape.Paper to Shape.Scissors) to Outcome.Loss,
            (Shape.Scissors to Shape.Rock) to Outcome.Loss,
            (Shape.Scissors to Shape.Paper) to Outcome.Win,
            (Shape.Scissors to Shape.Scissors) to Outcome.Draw,
        )
    }


    @Test
    fun `test that challange of all combinations leads to expected outcome`() {
        expect {
            Shape.values().forEach { player ->
                Shape.values().forEach { opponent ->
                    val description = "outcome of $player against $opponent"
                    val expected = runCatching { outcomes.getValue((player to opponent)) }
                    val actual = runCatching { Game.challenge(player, opponent) }

                    that(actual).describedAs(description).isEqualTo(expected)
                }
            }
        }
    }
}