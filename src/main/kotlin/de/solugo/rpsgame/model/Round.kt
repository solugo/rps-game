package de.solugo.rpsgame.model

data class Round(
    val player: Shape,
    val opponent: Shape,
    val outcome: Outcome,
)
