package com.chekhwastaken.gameoflife.engine.configuration

import com.chekhwastaken.gameoflife.engine.rule.RandomRule
import com.chekhwastaken.gameoflife.engine.rule.Rule

data class BoardConfiguration(
    val cellSize: Int = 0,
    val columnCount: Int = 1,
    val rowCount: Int = 1,
    val rule: Rule = RandomRule()
)