package com.poquiz.presentation.login

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.poquiz.domain.model.User
import com.poquiz.domain.usecase.user.LoginUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import java.lang.Exception
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val loginUseCase: LoginUseCase
): ViewModel() {

    private val _loginResult = MutableLiveData<String>()
    val loginResult: LiveData<String>
        get() = _loginResult

    private val _userInfo = MutableLiveData<User>()
    val userInfo : LiveData<User>
        get() = _userInfo

    fun login(user: User){
        viewModelScope.launch {
            try {
                val response = loginUseCase.invoke(user)

                if (response.id == "none" && response.pw == "none")
                    _loginResult.value = LOGIN_FAIL
                else {
                    _userInfo.value = response
                    _loginResult.value = LOGIN_SUCCESS
                }
            }catch (e: Exception){
                Log.d(TAG, "login: ${e.message}")
                    _loginResult.value = NET_ERR
            }
        }
    }

    companion object{
        const val LOGIN_SUCCESS = "로그인이 되었단다!"
        const val LOGIN_FAIL = "계정 정보가 잘못 되었단다!"
        const val NET_ERR = "네트워크에 문제가 있구나!"
    }
}