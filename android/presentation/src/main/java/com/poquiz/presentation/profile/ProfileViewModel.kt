package com.poquiz.presentation.profile

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import java.lang.Exception
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(
): ViewModel() {

    private val _updateResult = MutableLiveData<Boolean>()
     val updateResult: LiveData< Boolean>
         get() = _updateResult

    private val _isDupNick = MutableLiveData<Boolean>()
    val isDupNick: LiveData<Boolean>
        get() = _isDupNick

    private val _toastMsg = MutableLiveData<String>()
    val toastMsg: LiveData<String>
        get() = _toastMsg


    fun setUpdateResultFalse(){
        _updateResult.value = false
    }
}