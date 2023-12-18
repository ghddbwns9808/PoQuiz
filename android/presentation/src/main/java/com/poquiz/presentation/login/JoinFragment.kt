package com.poquiz.presentation.login

import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.os.Handler
import android.text.Editable
import android.text.InputFilter
import android.text.TextWatcher
import android.util.Log
import android.view.KeyEvent
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.View.OnFocusChangeListener
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import androidx.core.content.ContextCompat
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.viewModels
import com.poquiz.domain.model.User
import com.poquiz.presentation.LoginActivity
import com.poquiz.presentation.MainActivity
import com.poquiz.presentation.R
import com.poquiz.presentation.databinding.FragmentJoinBinding
import com.ssafy.template.board.config.BaseFragment
import dagger.hilt.android.AndroidEntryPoint
import java.util.regex.Pattern

@AndroidEntryPoint
class JoinFragment : BaseFragment<FragmentJoinBinding>(
    FragmentJoinBinding::bind, R.layout.fragment_join
) {
    private val viewModel : JoinViewModel by viewModels()
    private lateinit var loginActivity: LoginActivity

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        loginActivity = _activity as LoginActivity

        registerListener()
        registerObserver()

    }

    private fun registerObserver(){
        viewModel.isDupId.observe(viewLifecycleOwner){
            //중복된 아이디?
            if (it){
                binding.etId.isErrorEnabled = true
                binding.etId.helperText = ""
                binding.etId.error = "중복된 아이디란다!"
            }else{
                binding.etId.isErrorEnabled = false
                binding.etId.helperText = "사용 가능한 아이디란다!"
            }
        }
        viewModel.isDupNick.observe(viewLifecycleOwner){
            //중복된 아이디?
            if (it){
                binding.etNick.isErrorEnabled = true
                binding.etNick.helperText = ""
                binding.etNick.error = "중복된 닉네임이란다!"
            }else{
                binding.etNick.isErrorEnabled = false
                binding.etNick.helperText = "사용 가능한 닉네임이란다!"
            }
        }
        viewModel.isValidPw.observe(viewLifecycleOwner){
            //유효한 비밀번호
            if (it){
                binding.etPw.isErrorEnabled = false
                binding.etPw.helperText = "유효한 비밀번호란다!"
            }else{
                binding.etPw.isErrorEnabled = true
                binding.etPw.helperText = ""
                binding.etPw.error = "8자 이상, 영문 숫자만 가능!"
            }
        }

        viewModel.joinResult.observe(viewLifecycleOwner){
            val pref = requireContext().getSharedPreferences("user_info", Context.MODE_PRIVATE)
            pref.edit().run {
                putString("id", it.id)
                putString("nickname", it.nickname)
            }.commit()

            Handler().postDelayed({
                val intent = Intent(loginActivity, MainActivity::class.java)
                startActivity(intent)
                loginActivity.finish()
            }, 1500)
        }

    }

    private fun registerListener(){
        val vibration = AnimationUtils.loadAnimation(requireContext(), R.anim.edit_text_vibration)

        binding.etId.editText!!.addTextChangedListener(object : TextWatcher{
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                viewModel.isDuplicatedId(p0.toString().trim())
            }

            override fun afterTextChanged(p0: Editable?) {
            }
        })

        binding.etNick.editText!!.addTextChangedListener(object : TextWatcher{
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                viewModel.isDuplicatedNickname(p0.toString().trim())
            }

            override fun afterTextChanged(p0: Editable?) {
            }
        })

        binding.etPw.editText!!.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                viewModel.isValidatePw(p0.toString().trim())
            }

            override fun afterTextChanged(p0: Editable?) {}
        })

        binding.btnJoin.addClickListener {
            if (binding.etId.editText!!.text.trim().isBlank()
                || binding.etPw.editText!!.text.trim().isBlank()
                || binding.etNick.editText!!.text.trim().isBlank()){

                loginActivity.showCustomToast("모든 값을 입력하렴!")

                binding.linearLayoutCompat.startAnimation(vibration)
            }

            else if (viewModel.isDupId.value!! || viewModel.isDupNick.value!! || !viewModel.isValidPw.value!!){
                loginActivity.showCustomToast("잘못된 값이 있구나!")
                binding.linearLayoutCompat.startAnimation(vibration)
            }else{
                val user = User(binding.etId.editText!!.text.trim().toString(),
                binding.etPw.editText!!.text.trim().toString(),
                binding.etNick.editText!!.text.trim().toString())

                viewModel.join(user)
            }
        }

        binding.etId.editText!!.setOnKeyListener { view, i, keyEvent ->
            if ((keyEvent.action == KeyEvent.ACTION_DOWN) && (i == KeyEvent.KEYCODE_ENTER)) {
                hideKeyboard()
                true
            }else
                false
        }

        binding.etNick.editText!!.setOnKeyListener { view, i, keyEvent ->
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




}