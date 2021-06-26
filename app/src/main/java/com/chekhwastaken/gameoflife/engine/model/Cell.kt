package com.chekhwastaken.gameoflife.engine.model

sealed class Cell(val x: Int, val y: Int) : BoardItem() {
    class Dead(x: Int, y: Int) : Cell(x, y)
    class Alive(x: Int, y: Int) : Cell(x, y)
}
