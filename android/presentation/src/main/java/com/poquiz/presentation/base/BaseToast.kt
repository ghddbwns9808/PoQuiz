package com.poquiz.presentation.base

import android.content.Context
import android.content.res.Resources
import android.view.Gravity
import android.view.LayoutInflater
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import com.poquiz.presentation.R
import com.poquiz.presentation.databinding.PoquizToastBinding

object BaseToast {

    fun createToast(context: Context, msg: String): Toast?{
        val inflater = LayoutInflater.from(context)
        val binding: PoquizToastBinding
            = DataBindingUtil.inflate(inflater, R.layout.poquiz_toast, null, false)

        binding.tvContent.text = msg

        return Toast(context).apply {
            setGravity(Gravity.BOTTOM or Gravity.CENTER, 0, 16.toPx())
            duration = Toast.LENGTH_SHORT
            view = binding.root
        }
    }

    fun Int.toPx(): Int = (this * Resources.getSystem().displayMetrics.density).toInt()
}