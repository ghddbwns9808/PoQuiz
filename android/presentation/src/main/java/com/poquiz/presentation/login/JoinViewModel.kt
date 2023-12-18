package com.poquiz.presentation.login

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.poquiz.domain.model.User
import com.poquiz.domain.usecase.user.IdDuplicatedCheckUseCase
import com.poquiz.domain.usecase.user.JoinUseCase
import com.poquiz.domain.usecase.user.NicknameDuplicatedCheckUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import java.lang.Exception
import java.util.regex.Pattern
import javax.inject.Inject


@HiltViewModel
class JoinViewModel @Inject constructor(
    private val idDuplicatedCheckUseCase: IdDuplicatedCheckUseCase,
    private val nicknameDuplicatedCheckUseCase: NicknameDuplicatedCheckUseCase,
    private val joinUseCase: JoinUseCase
):ViewModel() {
    private val _isDupId = MutableLiveData<Boolean>()
    val isDupId: LiveData<Boolean>
        get() = _isDupId

    private val _isDupNick = MutableLiveData<Boolean>()
    val isDupNick: LiveData<Boolean>
        get() = _isDupNick

    private val _isValidPw = MutableLiveData<Boolean>()
    val isValidPw: LiveData<Boolean>
        get() = _isValidPw

    private val _toastMsg = MutableLiveData<String>()
    val toastMsg: LiveData<String>
        get() = _toastMsg

    private val _joinResult = MutableLiveData<User>()
    val joinResult : LiveData<User>
        get() = _joinResult

    fun isDuplicatedId(id: String){
        viewModelScope.launch {
            try {
                _isDupId.value = idDuplicatedCheckUseCase(id)!!
            } catch (e: Exception){
                _toastMsg.value = LoginViewModel.NET_ERR
            }
        }
    }

    fun isDuplicatedNickname(nickname: String){
        Log.d(TAG, "isDuplicatedNickname: ")
        viewModelScope.launch {
            try {
                _isDupNick.value = nicknameDuplicatedCheckUseCase(nickname)!!
                Log.d(TAG, "isDuplicatedNickname: ${isDupNick.value}")
            } catch (e: Exception){
                Log.d(TAG, "isDuplicatedNickname: 11")
                _toastMsg.value = LoginViewModel.NET_ERR
            }
        }
    }

    fun isValidatePw(pw: String){
        _isValidPw.value =
            (Pattern.matches("^[a-zA-Z0-9\\u318D\\u119E\\u11A2\\u2022\\u2025a\\u00B7\\uFE55]+$", pw)
                && pw.trim().length > 7)
    }

    fun join(user: User){
        viewModelScope.launch {
            try {
                _joinResult.value = joinUseCase(user)!!
            }catch (e: Exception){
                _toastMsg.value = LoginViewModel.NET_ERR
            }
        }
    }
}