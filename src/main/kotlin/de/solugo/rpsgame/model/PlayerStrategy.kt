package de.solugo.rpsgame.model

sealed class PlayerStrategy(
    val decide: () -> Shape,
) {
    object Random : PlayerStrategy({ Shape.values().random() })
    class Constant(shape: Shape) : PlayerStrategy({ shape })
    class Custom(decision: () -> Shape) : PlayerStrategy(decision)
}