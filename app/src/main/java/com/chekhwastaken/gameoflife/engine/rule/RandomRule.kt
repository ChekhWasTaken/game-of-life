package com.chekhwastaken.gameoflife.engine.rule

import com.chekhwastaken.gameoflife.engine.model.Cell
import kotlin.random.Random

class RandomRule : Rule {
    private val random = Random(System.currentTimeMillis())

    override fun calculateCellAt(x: Int, y: Int): Cell = random.nextBoolean().takeIf { it }?.let { Cell.Alive(x, y) } ?: Cell.Dead(x, y)
}
