package com.chekhwastaken.gameoflife.engine.model

import com.chekhwastaken.gameoflife.engine.configuration.BoardConfiguration
import com.chekhwastaken.gameoflife.engine.configuration.Configuration

data class State(
    val configuration: Configuration = Configuration(),
    val board: Board = Board(configuration.boardConfiguration),
)

class Board(boardConfiguration: BoardConfiguration) : Iterable<BoardItem> {

    private val board: Array<Array<Cell>> = Array(boardConfiguration.columnCount) { x ->
        Array(boardConfiguration.rowCount) { y -> boardConfiguration.rule.calculateCellAt(x, y) }
    }

    fun getCellAt(x: Int, y: Int) = board[x][y]

    override fun iterator(): Iterator<BoardItem> = BoardItemIterator(board)
}

private class BoardItemIterator(private val board: Array<Array<Cell>>) : Iterator<BoardItem> {
    private var index = 0

    private val columnCount: Int
        get() = board.size

    private val rowCount: Int
        get() = board[0].size

    private lateinit var currentItem: BoardItem

    override fun hasNext(): Boolean = index < columnCount * rowCount

    override fun next(): BoardItem {
        currentItem =
            if (index > 0 && index % columnCount == 0 && currentItem != BoardItem.NextLine) {
                BoardItem.NextLine
            } else {
                val x = index % columnCount
                val y = index / columnCount
                index++
                board[x][y]
            }

        return currentItem
    }
}


