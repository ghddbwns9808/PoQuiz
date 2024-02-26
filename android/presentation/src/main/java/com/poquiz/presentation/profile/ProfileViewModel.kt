package com.poquiz.presentation.profile

import android.provider.ContactsContract.CommonDataKinds.Nickname
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.poquiz.domain.model.User
import com.poquiz.domain.usecase.profile.NicknameUpdateUseCase
import com.poquiz.domain.usecase.user.NicknameDuplicatedCheckUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import java.lang.Exception
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(
    private val nicknameUpdateUseCase: NicknameUpdateUseCase,
    private val nicknameDuplicatedCheckUseCase: NicknameDuplicatedCheckUseCase
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

    fun isDupNick(nickname: String) {
        viewModelScope.launch {
            try {
                _isDupNick.value = nicknameDuplicatedCheckUseCase(nickname)!!
            } catch (e: Exception){
            }
        }
    }

    fun updateNickname(user: User){
        viewModelScope.launch {
            try {
                _updateResult.value = nicknameUpdateUseCase(user)!!
            } catch (e: Exception){
            }
        }
    }

    fun setUpdateResultFalse(){
        _updateResult.value = false
    }
}