package com.chekhwastaken.gameoflife.engine.ui

import android.os.Bundle
import android.view.Window
import android.view.WindowManager
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.chekhwastaken.gameoflife.engine.configuration.BoardConfiguration
import com.chekhwastaken.gameoflife.engine.configuration.Configuration
import com.chekhwastaken.gameoflife.engine.ui.viewmodel.GameOfLifeViewModel

open class GameOfLifeActivity : AppCompatActivity() {
    private lateinit var gameView: GameView

    private val viewModel: GameOfLifeViewModel by viewModels()

    open val iterationSpeed: Long = 500L
    open val resolution: Int = 20

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN)

        val columnCount = resources.displayMetrics.widthPixels / resolution
        val rowCount = resources.displayMetrics.heightPixels / resolution


        gameView = GameView(this)
        setContentView(gameView)

        viewModel.initWith(
            Configuration(
                iterationSpeed = iterationSpeed,
                resolution = resolution,
                boardConfiguration = BoardConfiguration(
                    cellSize = resolution,
                    columnCount = columnCount,
                    rowCount = rowCount
                )
            )
        )

        viewModel.gameStateLiveData.observe(this) {
            gameView.state = it
        }
    }
}