package com.poquiz.presentation.game

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import com.poquiz.presentation.databinding.DlgInsertRankBinding

class RegisterRankDialog(
    clickInterface: RegisterRankDialogClickInterface
) : DialogFragment(){


    private var _binding: DlgInsertRankBinding? = null
    private val binding get() = _binding!!

    private var clickInterface: RegisterRankDialogClickInterface? = null

    init {
        this.clickInterface = clickInterface
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = DlgInsertRankBinding.inflate(layoutInflater)

        // 레이아웃 배경을 투명하게 해줌, 필수 아님
        dialog?.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))

        // 취소 버튼 클릭
        binding.tvCancel.setOnClickListener {
            dismiss()
        }

        // 확인 버튼 클릭
        binding.tvRegister.setOnClickListener {
            this.clickInterface?.onRegisterButtonClick(
                binding.etName.editText!!.text.toString()
            )
            dismiss()
        }

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}

interface RegisterRankDialogClickInterface {
    fun onRegisterButtonClick(nickName: String)

}
