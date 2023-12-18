package com.poquiz.presentation.ranking

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.poquiz.domain.model.Rank
import com.poquiz.domain.usecase.rank.GetHighRankUseCase
import com.poquiz.domain.usecase.rank.GetHighestRankUseCase
import com.poquiz.domain.usecase.rank.GetLowRankUseCase
import com.poquiz.domain.usecase.rank.GetNormalRankUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RankingViewModel @Inject constructor(
    private val getLowRankUseCase: GetLowRankUseCase,
    private val getNormalRankUseCase: GetNormalRankUseCase,
    private val getHighRankUseCase: GetHighRankUseCase,
    private val getHighestRankUseCase: GetHighestRankUseCase
): ViewModel() {

    private val _lowRank = MutableLiveData<List<Rank>>()
    val lowRank : LiveData<List<Rank>>
        get() = _lowRank

    private val _normalRank = MutableLiveData<List<Rank>>()
    val normalRank : LiveData<List<Rank>>
        get() = _normalRank

    private val _highRank = MutableLiveData<List<Rank>>()
    val highRank : LiveData<List<Rank>>
        get() = _highRank

    private val _highestRank = MutableLiveData<List<Rank>>()
    val highestRank : LiveData<List<Rank>>
        get() = _highestRank

    private val _loadFinished = MutableLiveData<Boolean>(false)
    val loadFinished: LiveData<Boolean>
        get() = _loadFinished

    fun loadAllRank(){
        viewModelScope.launch {
            val lowJob = async {
                getLowRankUseCase()
            }
            val normalJob = async {
                getNormalRankUseCase()
            }
            val highJob = async {
                getHighRankUseCase()
            }
            val highestJob = async {
                getHighestRankUseCase()
            }
            _lowRank.value = lowJob.await()
            _normalRank.value = normalJob.await()
            _highRank.value = highJob.await()
            _highestRank.value = highestJob.await()

            _loadFinished.value = true
        }
    }


}