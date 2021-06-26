package com.chekhwastaken.gameoflife.engine.ui.viewmodel

import android.os.Handler
import android.os.HandlerThread
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.chekhwastaken.gameoflife.engine.configuration.Configuration
import com.chekhwastaken.gameoflife.engine.model.State
import com.chekhwastaken.gameoflife.engine.rule.WrapAroundClassicRule
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class GameOfLifeViewModel : ViewModel() {
    private val handlerThread: HandlerThread = HandlerThread("backgroundThread").also {
        it.start()
    }

    private val handler = Handler(handlerThread.looper)

    private lateinit var iterationRunnable: Runnable

    private val _gameStateLiveData: MutableLiveData<State> = MutableLiveData()

    val gameStateLiveData: LiveData<State>
        get() = _gameStateLiveData

    fun initWith(configuration: Configuration) = viewModelScope.launch(Dispatchers.IO) {
        State(configuration).also {
            _gameStateLiveData.postValue(it)
        }

        iterationRunnable = Runnable {
            performIteration()
            handler.postDelayed(iterationRunnable, configuration.iterationSpeed)
        }

        handler.postDelayed(iterationRunnable, configuration.iterationSpeed)
    }

    private fun performIteration() {
        val currentState = _gameStateLiveData.value ?: return
        val newBoardConfiguration = currentState.configuration.boardConfiguration.copy(rule = WrapAroundClassicRule(currentState))
        val newConfiguration = currentState.configuration.copy(boardConfiguration = newBoardConfiguration)
        _gameStateLiveData.postValue(State(newConfiguration))
    }
}