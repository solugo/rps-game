package de.solugo.rpsgame.model

data class Statistics(
    val outcomes: Map<Outcome, Int>,
    val playerDecisions: Map<Shape, Int>,
    val opponentDecisions: Map<Shape, Int>,
)