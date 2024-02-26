package com.poquiz.presentation.profile

import android.annotation.SuppressLint
import android.app.AlertDialog
import android.content.Context
import android.content.SharedPreferences
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.KeyEvent
import android.view.LayoutInflater
import android.view.View
import android.widget.TextView
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.viewModels
import com.google.android.material.textfield.TextInputLayout
import com.poquiz.presentation.MainActivity
import com.poquiz.presentation.R
import com.poquiz.presentation.databinding.FragmentProfileBinding
import com.ssafy.template.board.config.BaseFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ProfileFragment : BaseFragment<FragmentProfileBinding>(
    FragmentProfileBinding::bind, R.layout.fragment_profile
) {
    private val viewModel: ProfileViewModel by viewModels()
    private lateinit var mainActivity: MainActivity
    private lateinit var pref: SharedPreferences
    private lateinit var changedNickname: String

    private var backPressedTime = 0L
    private val onBackPressedCallback = object : OnBackPressedCallback(true) {
        override fun handleOnBackPressed() {
            if (System.currentTimeMillis() - backPressedTime <= 2000) {
                mainActivity.finish()
            } else {
                backPressedTime = System.currentTimeMillis()
                mainActivity.showCustomToast("한번 더 누르면 종료 된단다!")
            }
        }
    }

    @SuppressLint("SetTextI18n")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mainActivity = _activity as MainActivity
        mainActivity.onBackPressedDispatcher.addCallback(viewLifecycleOwner, onBackPressedCallback)

        pref = requireContext().getSharedPreferences("user_info", Context.MODE_PRIVATE)
        setNickname()

    }

    private fun setNickname(){
        binding.tvNickname.setText("나 ${pref.getString("nickname", "닉네임")}!")
    }

}