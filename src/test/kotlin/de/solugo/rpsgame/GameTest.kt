package de.solugo.rpsgame

import de.solugo.rpsgame.model.*
import org.junit.jupiter.api.Test
import strikt.api.expect
import strikt.assertions.*

class GameTest {

    companion object {
        private val iterations = (0 until 100)
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
                    val actual = runCatching { Game.decideOutcome(player, opponent) }

                    that(actual).describedAs(description).isEqualTo(expected)
                }
            }
        }
    }

    @Test
    fun `test that player with constant decision always returns the same decision`() {
        expect {
            val player = Player(PlayerStrategy.Constant(Shape.Rock))
            val description = "constant decisions"
            val actual = iterations.mapNotNull {
                runCatching { player.strategy.decide() }.getOrNull()
            }

            that(actual.distinct()).describedAs(description).hasSize(1)
        }
    }

    @Test
    fun `test that player with random decision returns varying decisions`() {
        expect {
            val player = Player(PlayerStrategy.Random)
            val description = "random decision"
            val actual = iterations.mapNotNull {
                runCatching { player.strategy.decide() }.getOrNull()
            }

            that(actual.distinct()).describedAs(description).hasSize(3)
        }
    }

    @Test
    fun `test that playing a given number of rounds leads to an equal number of results`() {
        expect {
            val rounds = 100
            val player = Player(PlayerStrategy.Random)
            val opponent = Player(PlayerStrategy.Random)
            val description = "a game of $rounds rounds"
            val actual = runCatching { Game.playRounds(player, opponent, rounds) }.takeIf { it.isSuccess }?.getOrNull()

            that(actual).describedAs(description).isNotNull().hasSize(rounds)
        }
    }

    @Test
    fun `test that statistics for given rounds are calculated`() {
        expect {
            val player = Player(PlayerStrategy.Random)
            val opponent = Player(PlayerStrategy.Random)
            val description = "statistics"
            val rounds = Game.playRounds(player, opponent, 100)
            val actual = kotlin.runCatching { Game.calculateStatistics(rounds) }.getOrNull()

            that(actual).describedAs(description).isNotNull().and {
                get(Statistics::outcomes).isNotEmpty().get("sum") { values.sum() }.isNotEqualTo(0)
                get(Statistics::opponentDecisions).isNotEmpty().get("sum") { values.sum() }.isNotEqualTo(0)
                get(Statistics::playerDecisions).isNotEmpty().get("sum") { values.sum() }.isNotEqualTo(0)
            }
        }
    }

}