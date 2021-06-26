package com.chekhwastaken.gameoflife

import com.chekhwastaken.gameoflife.engine.ui.GameOfLifeActivity

class MainActivity : GameOfLifeActivity() {
    override val iterationSpeed: Long
        get() = 200

    override val resolution: Int
        get() = 8

}