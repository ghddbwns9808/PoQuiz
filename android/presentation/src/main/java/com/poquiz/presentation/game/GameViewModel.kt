package com.poquiz.presentation.game

import android.os.CountDownTimer
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.poquiz.domain.usecase.game.PokemonImgUseCase
import com.poquiz.domain.usecase.game.PokemonInfoUseCase
import com.poquiz.presentation.model.Pokemon
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.async
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import java.lang.Exception
import javax.inject.Inject


@HiltViewModel
class GameViewModel @Inject constructor(
    private val pokemonImgUseCase: PokemonImgUseCase,
    private val pokemonInfoUseCase: PokemonInfoUseCase
): ViewModel() {

    private val  _isEnd = MutableLiveData<Boolean>(false)
    val isEnd : LiveData<Boolean>
        get() = _isEnd

    private val  _isTimeOut = MutableLiveData<Boolean>(false)
    val isTimeOut : LiveData<Boolean>
        get() = _isTimeOut

    private val _correctCnt = MutableLiveData<Int>(0)
    val correctCnt: LiveData<Int>
        get() = _correctCnt

    private val _remainTime = MutableLiveData<Int>(10)
    val remainTime: LiveData<Int>
        get() = _remainTime

    private val _quiz = MutableLiveData<Pokemon>()
    val quiz: LiveData<Pokemon>
        get() = _quiz


    private val countDownTimer = object : CountDownTimer(10000, 1000){
        override fun onTick(p0: Long) {
            _remainTime.value = _remainTime.value!!.minus(1)
        }
        override fun onFinish() {
            _isTimeOut.value = true
        }
    }


    fun loadPokeInfo(id: Int){
        viewModelScope.launch {
            try {
                val imgJob = async {
                    pokemonImgUseCase(id.toString())
                }
                val infoJob = async {
                    pokemonInfoUseCase(id.toString())
                }
                val info = infoJob.await()
                val pokemon = Pokemon(info.id, info.name, info.type, imgJob.await().img)

                _quiz.value = pokemon
                _remainTime.value = 11
                countDownTimer.start()
            }catch (e: Exception){
                Log.d(TAG, "loadPokeInfo: ")
            }
            
        }
    }

    fun stopTimer(){
        countDownTimer.cancel()
    }

    fun increaseCorrectCnt(){
        viewModelScope.launch {
            _correctCnt.value = _correctCnt.value!!.plus(1)
            delay(2000)

        }
    }

    fun endGame(){

        _isEnd.value = true

    }
}