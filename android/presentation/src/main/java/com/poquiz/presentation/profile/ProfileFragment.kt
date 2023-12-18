package com.poquiz.presentation.profile

import android.annotation.SuppressLint
import android.app.AlertDialog
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.KeyEvent
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.findFragment
import androidx.fragment.app.viewModels
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.google.android.material.textfield.TextInputLayout
import com.poquiz.domain.model.User
import com.poquiz.presentation.LoginActivity
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

        binding.btnChangeNickname.addClickListener {
            showDialog()
        }

        binding.btnLogout.addClickListener {
            val pref = requireContext().getSharedPreferences("user_info", Context.MODE_PRIVATE)
            pref.edit().clear().commit()
            val intent = Intent(mainActivity, LoginActivity::class.java)
            startActivity(intent)
            mainActivity.finish()
        }

    }

    private fun setNickname(){
        binding.tvNickname.setText("나 ${pref.getString("nickname", "닉네임")}!")
    }

    fun showDialog(){
        val builder = AlertDialog.Builder(mainActivity)
        val view = LayoutInflater.from(requireContext()).inflate(
            R.layout.dlg_change_nickname, mainActivity.findViewById(R.id.nickChangeDlg)
        )
        val nickName = view.findViewById<TextInputLayout>(R.id.etNickname)
        val btnCancel = view.findViewById<TextView>(R.id.tvCancle)
        val btnSave = view.findViewById<TextView>(R.id.tvSave)

        nickName.editText!!.setOnKeyListener { view, i, keyEvent ->
            if ((keyEvent.action == KeyEvent.ACTION_DOWN) && (i == KeyEvent.KEYCODE_ENTER)) {
                hideKeyboard()
                true
            }else
                false
        }

        builder.setView(view)
        val dialog = builder.create()
        dialog.apply {
            window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
            setCancelable(false)
        }.show()

        viewModel.updateResult.observe(viewLifecycleOwner){
            if(it){
//                dialog.dismiss()
                mainActivity.showCustomToast("닉네임이 변경됐다!")
                pref.edit().putString("nickname", changedNickname).apply()
                setNickname()
                viewModel.setUpdateResultFalse()
            }else{
//                mainActivity.showCustomToast("문제가 생겼군...")
            }
        }


        viewModel.isDupNick.observe(viewLifecycleOwner){
            //중복된 아이디?
            if (it){
                nickName.isErrorEnabled = true
                nickName.helperText = ""
                nickName.error = "중복된 닉네임이란다!"
            }else{
                nickName.isErrorEnabled = false
                nickName.helperText = "사용 가능한 닉네임이란다!"
            }
        }

        nickName.editText!!.addTextChangedListener(object :
            TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                viewModel.isDupNick(p0.toString().trim())
                changedNickname = p0.toString().trim()
            }

            override fun afterTextChanged(p0: Editable?) {
            }
        })

        btnCancel.setOnClickListener {
            dialog.dismiss()
        }

        btnSave.setOnClickListener {
            if (viewModel.isDupNick.value!!)
                mainActivity.showCustomToast("중복된 닉네임이다!")
            else{
                dialog.dismiss()
                val user = User(pref.getString("id", "-1")!!, "", nickName.editText!!.text.trim().toString())
                viewModel.updateNickname(user)
            }
        }
    }
}