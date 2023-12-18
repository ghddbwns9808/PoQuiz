package com.poquiz.presentation.customview

import android.content.Context
import android.content.res.TypedArray
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.FrameLayout
import com.poquiz.presentation.R
import com.poquiz.presentation.databinding.PoQuizButtonBinding

class PoQuizButton: FrameLayout {
    private var btnClickListener: (() -> Unit)? = null

    constructor(context: Context) : super(context) {
        initView()
    }

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {
        initView()
        getAttrs(attrs)
    }
    private val binding: PoQuizButtonBinding by lazy {
        PoQuizButtonBinding.bind(
            LayoutInflater.from(context).inflate(R.layout.po_quiz_button, this, false)
        )
    }
    private fun initView() {
        addView(binding.root)
    }

    private fun getAttrs(attrs: AttributeSet) {
        val typedArray = context.obtainStyledAttributes(attrs, R.styleable.PoQuizButton)
        setTypeArray(typedArray)
    }

    fun addClickListener(btnClickListener: (() -> Unit) ){
        this.btnClickListener = btnClickListener
    }

    fun click(){
        btnClickListener?.let { it() }
    }

    private fun setTypeArray(typedArray: TypedArray) {
        binding.run {
            tvContent.text = typedArray.getString(R.styleable.PoQuizButton_btnText)
            tvContent.textSize = typedArray.getDimension(R.styleable.PoQuizButton_textSize, 16F)

            myButton.setOnClickListener { _ ->
                btnClickListener?.let { it() }
            }
        }
        typedArray.recycle()
    }

}