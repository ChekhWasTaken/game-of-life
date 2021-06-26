package com.chekhwastaken.gameoflife.engine.configuration

import android.graphics.Color
import androidx.annotation.ColorInt

data class Configuration(
    @ColorInt val deadCellColor: Int = Color.BLACK,
    @ColorInt val aliveCellColor: Int = Color.WHITE,
    @ColorInt val backgroundColor: Int = Color.GREEN,
    val boardConfiguration: BoardConfiguration = BoardConfiguration(),
    val iterationSpeed: Long = Long.MAX_VALUE,
    val resolution: Int = Int.MIN_VALUE
)