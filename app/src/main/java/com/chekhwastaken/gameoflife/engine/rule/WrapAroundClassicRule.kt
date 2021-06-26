package com.chekhwastaken.gameoflife.engine.rule

import com.chekhwastaken.gameoflife.engine.model.Cell
import com.chekhwastaken.gameoflife.engine.model.State

open class WrapAroundClassicRule(state: State) : ClassicRule(state) {
    override fun getNeighborsFor(x: Int, y: Int): List<Cell> {
        val neighbors = mutableListOf<Cell>()

        val columnCount = state.configuration.boardConfiguration.columnCount
        val rowCount = state.configuration.boardConfiguration.rowCount

        for (neighborX in (-1..1)) {
            for (neighborY in (-1..1)) {
                if (neighborX == 0 && neighborY == 0) continue

                val actualNeighborX = (x + neighborX + columnCount) % columnCount
                val actualNeighborY = (y + neighborY + rowCount) % rowCount

                neighbors.add(state.board.getCellAt(actualNeighborX, actualNeighborY))
            }
        }

        return neighbors
    }
}