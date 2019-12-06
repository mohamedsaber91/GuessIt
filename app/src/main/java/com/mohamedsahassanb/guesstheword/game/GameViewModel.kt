package com.mohamedsahassanb.guesstheword.game

import android.os.CountDownTimer
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class GameViewModel : ViewModel() {

    companion object {
        // These represent different important times
        // This is when the game is over
        const val DONE = 0L
        // This is the number of milliseconds in a second
        const val ONE_SECOND = 1000L
        // This is the total time of the game
        const val COUNTDOWN_TIME = 60000L
    }

    private val timer :CountDownTimer

    private val _currentTime = MutableLiveData<Long>()
    val currentTime: LiveData<Long>
        get() = _currentTime

     private val _word = MutableLiveData<String>()
    val word : LiveData<String>
    get() = _word

    private var _score = MutableLiveData<Int>()
    val score : LiveData<Int>
    get() = _score

    private var _eventGameFinished = MutableLiveData<Boolean>()
    val eventGameFinished :LiveData<Boolean>
    get() = _eventGameFinished


    private lateinit var wordList: MutableList<String>
    init {
        Log.e("GameViewModel","GameViewModel created!")
        resetList()
        nextWord()
        _score.value=0
        _eventGameFinished.value=false

        timer = object : CountDownTimer(COUNTDOWN_TIME, ONE_SECOND) {

            override fun onTick(millisUntilFinished: Long) {
                _currentTime.value=(millisUntilFinished/ ONE_SECOND)
            }

            override fun onFinish() {
                _currentTime.value= DONE
                _eventGameFinished.value=true
            }
        }

        timer.start()

    }

    override fun onCleared() {
        super.onCleared()
        Log.e("GameViewModel", "GameViewModel destroyed")
        timer.cancel()
    }

    private fun resetList(){
        wordList = mutableListOf(
            "queen",
            "hospital",
            "basketball",
            "cat",
            "change",
            "snail",
            "soup",
            "calendar",
            "sad",
            "desk",
            "guitar",
            "home",
            "railway",
            "zebra",
            "jelly",
            "car",
            "crow",
            "trade",
            "bag",
            "roll",
            "bubble"
        )

        wordList.shuffle()

    }

    private fun nextWord(){
        if (wordList.isEmpty()){
           resetList()
        }else{
            _word.value = wordList.removeAt(0)
        }

    }

     fun onSkip(){
        _score.value = (score.value)?.minus(1)
        nextWord()
    }
     fun onCorrect(){
        _score.value = (score.value)?.plus(1)
        nextWord()
    }

    fun onGameFinished(){
        _eventGameFinished.value=false
    }


}