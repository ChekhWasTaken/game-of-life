package com.chekhwastaken.gameoflife.engine.ui

import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.Rect
import android.util.AttributeSet
import android.view.View
import com.chekhwastaken.gameoflife.engine.model.BoardItem
import com.chekhwastaken.gameoflife.engine.model.Cell
import com.chekhwastaken.gameoflife.engine.model.State

class GameView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyle: Int = 0
) : View(context, attrs, defStyle) {
    private val cellPaint = Paint().apply {
        style = Paint.Style.FILL
    }

    private val textPaint = Paint()

    private val cellRect = Rect()

    var state: State = State()
        set(value) {
            field = value
            invalidate()
        }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)

        canvas.drawColor(state.configuration.backgroundColor)
        val cellSize = state.configuration.boardConfiguration.cellSize
        var (x, y) = (0 to 0)

        state.board.forEach { boardItem ->
            when (boardItem) {
                is Cell -> {
                    val color = boardItem.takeIf { it is Cell.Alive }
                        ?.let { state.configuration.aliveCellColor }
                        ?: state.configuration.deadCellColor
                    cellPaint.color = color

                    cellRect.set(x, y, x + cellSize, y + cellSize)

                    canvas.drawRect(cellRect, cellPaint)

                    textPaint.color = boardItem.takeIf { it is Cell.Alive }
                        ?.let { state.configuration.deadCellColor }
                        ?: state.configuration.aliveCellColor

                    textPaint.textSize = cellSize.toFloat() / 8
                    canvas.drawText("${boardItem.x}x${boardItem.y}", cellRect.exactCenterX(), cellRect.exactCenterY(), textPaint)

                    x += cellSize
                }
                BoardItem.NextLine -> {
                    y += cellSize
                    x = 0
                }
            }
        }
    }
}