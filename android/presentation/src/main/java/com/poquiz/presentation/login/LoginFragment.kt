package com.poquiz.presentation.login

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.view.KeyEvent
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.viewModels
import com.poquiz.domain.model.User
import com.poquiz.presentation.LoginActivity
import com.poquiz.presentation.MainActivity
import com.poquiz.presentation.R
import com.poquiz.presentation.base.Constants
import com.poquiz.presentation.base.Constants.NAVIGATE_TO_JOIN
import com.poquiz.presentation.databinding.FragmentLoginBinding
import com.ssafy.template.board.config.BaseFragment
import dagger.hilt.android.AndroidEntryPoint

private const val TAG = "LoginFragment_hong"

@AndroidEntryPoint
class LoginFragment : BaseFragment<FragmentLoginBinding>(
    FragmentLoginBinding::bind, R.layout.fragment_login
) {
    private val viewModel: LoginViewModel by viewModels()
    private lateinit var loginActivity: LoginActivity
    private var autoLogin = false

    private var backPressedTime = 0L

    private val onBackPressedCallback = object : OnBackPressedCallback(true) {
        override fun handleOnBackPressed() {
            if (System.currentTimeMillis() - backPressedTime <= 2000) {
                loginActivity.finish()
            } else {
                backPressedTime = System.currentTimeMillis()
                loginActivity.showCustomToast("한번 더 누르면 종료 된단다!")
            }
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        loginActivity = _activity as LoginActivity
        loginActivity.onBackPressedDispatcher.addCallback(viewLifecycleOwner, onBackPressedCallback)
        registerListener()
        registerObserver()

    }

    private fun registerListener(){
        val vibration = AnimationUtils.loadAnimation(requireContext(), R.anim.edit_text_vibration)
        binding.btnLogin.addClickListener {
            val id = binding.etId.editText!!.text.toString().trim()
            val pw = binding.etPw.editText!!.text.toString().trim()

            if (id.isBlank() || pw.isBlank()){
                loginActivity.showCustomToast("아이디 비밀번호를 모두 입력하세요")
                binding.llInput.startAnimation(vibration)
            }else{
                viewModel.login(User(id, pw, ""))
            }
        }

        binding.cbAutoLogin.setOnCheckedChangeListener { compoundButton, b -> 
            compoundButton.startAnimation(vibration)
            autoLogin = b
            Log.d(TAG, "registerListener: $autoLogin")
        } 

        binding.tvJoin.setOnClickListener {
            loginActivity.navigate(NAVIGATE_TO_JOIN)
        }

        binding.etId.editText!!.setOnKeyListener { view, i, keyEvent ->
            if ((keyEvent.action == KeyEvent.ACTION_DOWN) && (i == KeyEvent.KEYCODE_ENTER)) {
                hideKeyboard()
                true
            }else
                false
        }

        binding.etPw.editText!!.setOnKeyListener { view, i, keyEvent ->
            if ((keyEvent.action == KeyEvent.ACTION_DOWN) && (i == KeyEvent.KEYCODE_ENTER)) {
                hideKeyboard()
                true
            }else
                false
        }
    }

    private fun registerObserver(){

        viewModel.userInfo.observe(viewLifecycleOwner){
            val pref = requireContext().getSharedPreferences("user_info", Context.MODE_PRIVATE)
            pref.edit().run {
                putString("id", it.id)
                putString("nickname", it.nickname)
            }.commit()
        }

        viewModel.loginResult.observe(viewLifecycleOwner){
            loginActivity.showCustomToast(it)
            if(it != LoginViewModel.LOGIN_FAIL && it != LoginViewModel.NET_ERR){
                val pref = requireContext().getSharedPreferences("user_info", Context.MODE_PRIVATE)
                pref.edit().putBoolean("autoLogin", autoLogin).commit()

                Handler().postDelayed({
                    val intent = Intent(loginActivity, MainActivity::class.java)
                    startActivity(intent)
                    loginActivity.finish()
                }, 1500)
            }
        }
    }

}