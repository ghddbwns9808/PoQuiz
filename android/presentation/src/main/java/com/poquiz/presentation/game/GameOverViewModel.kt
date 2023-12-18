package com.poquiz.presentation.game

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.poquiz.domain.model.Rank
import com.poquiz.domain.usecase.rank.RegistHighRankUseCase
import com.poquiz.domain.usecase.rank.RegistHighestRankUseCase
import com.poquiz.domain.usecase.rank.RegistLowRankUseCase
import com.poquiz.domain.usecase.rank.RegistNormalRankUseCase
import com.poquiz.presentation.base.Constants
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import java.lang.Exception
import javax.inject.Inject

@HiltViewModel
class GameOverViewModel @Inject constructor(
    private val registLowRankUseCase: RegistLowRankUseCase,
    private val registNormalRankUseCase: RegistNormalRankUseCase,
    private val registHighRankUseCase: RegistHighRankUseCase,
    private val registHighestRankUseCase: RegistHighestRankUseCase
): ViewModel() {
    private val _rankUpdateResult = MutableLiveData<String>()
    val rankUpdateResult: LiveData<String>
        get() = _rankUpdateResult

    fun updateLowRank(rank: Rank){
        viewModelScope.launch {
            try {
                val response = registLowRankUseCase(rank)
                if (response)
                    _rankUpdateResult.value = Constants.RANK_UPDATE_SUCCESS
                else
                    _rankUpdateResult.value = Constants.RANK_UPDATE_FAIL
            }catch (e: Exception){
                _rankUpdateResult.value = Constants.NET_ERR
            }
        }
    }

    fun updateNormalRank(rank: Rank){
        viewModelScope.launch {
            try {
                val response = registNormalRankUseCase(rank)
                if (response)
                    _rankUpdateResult.value = Constants.RANK_UPDATE_SUCCESS
                else
                    _rankUpdateResult.value = Constants.RANK_UPDATE_FAIL
            }catch (e: Exception){
                _rankUpdateResult.value = Constants.NET_ERR
            }
        }
    }

    fun updateHighRank(rank: Rank){
        viewModelScope.launch {
            try {
                val response = registHighRankUseCase(rank)
                if (response)
                    _rankUpdateResult.value = Constants.RANK_UPDATE_SUCCESS
                else
                    _rankUpdateResult.value = Constants.RANK_UPDATE_FAIL
            }catch (e: Exception){
                _rankUpdateResult.value = Constants.NET_ERR
            }
        }
    }

    fun updateHighestRank(rank: Rank){
        viewModelScope.launch {
            try {
                val response = registHighestRankUseCase(rank)
                if (response)
                    _rankUpdateResult.value = Constants.RANK_UPDATE_SUCCESS
                else
                    _rankUpdateResult.value = Constants.RANK_UPDATE_FAIL
            }catch (e: Exception){
                _rankUpdateResult.value = Constants.NET_ERR
            }
        }
    }
}