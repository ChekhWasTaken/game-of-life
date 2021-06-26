package com.chekhwastaken.gameoflife.engine.rule

import com.chekhwastaken.gameoflife.engine.model.Cell

interface Rule {
    fun calculateCellAt(x: Int, y: Int): Cell
}
