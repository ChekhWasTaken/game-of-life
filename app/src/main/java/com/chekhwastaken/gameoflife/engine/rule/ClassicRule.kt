package com.chekhwastaken.gameoflife.engine.rule

import com.chekhwastaken.gameoflife.engine.model.Cell
import com.chekhwastaken.gameoflife.engine.model.State

abstract class ClassicRule(state: State) : StateBasedRule(state) {
    override fun calculateCellAt(x: Int, y: Int): Cell {
        val aliveCount = getNeighborsFor(x, y).count { it is Cell.Alive }

        return when (state.board.getCellAt(x, y)) {
            is Cell.Alive -> aliveCount.takeIf { it < 2 || it > 3 }?.let { Cell.Dead(x, y) } ?: Cell.Alive(x, y)
            is Cell.Dead -> aliveCount.takeIf { it == 3 }?.let { Cell.Alive(x, y) } ?: Cell.Dead(x, y)
        }
    }

    abstract fun getNeighborsFor(x: Int, y: Int): List<Cell>
}
