package de.solugo.rpsgame.model

class Player(val decision: () -> Shape) {
    companion object {
        val DECISION_ROCK = { Shape.Rock }
        val DECISION_RANDOM = { Shape.values().random() }
    }
}