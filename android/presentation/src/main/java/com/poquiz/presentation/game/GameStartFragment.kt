package com.poquiz.presentation.game

import android.os.Bundle
import android.view.View
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.viewModels
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.poquiz.presentation.MainActivity
import com.poquiz.presentation.R
import com.poquiz.presentation.base.Constants.NAVIGATE_TO_GAME
import com.poquiz.presentation.databinding.FragmentGameStartBinding
import com.ssafy.template.board.config.BaseFragment
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class GameStartFragment : BaseFragment<FragmentGameStartBinding>(
    FragmentGameStartBinding::bind, R.layout.fragment_game_start
) {
    private var difficulty: Int = -1
    private lateinit var mainActivity: MainActivity

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
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mainActivity = _activity as MainActivity
        mainActivity.onBackPressedDispatcher.addCallback(viewLifecycleOwner, onBackPressedCallback)

        mainActivity.findViewById<BottomNavigationView>(R.id.btmNav).visibility = View.VISIBLE

        binding.toggleButton.addOnButtonCheckedListener { group, checkedId, isChecked ->
            when(checkedId){
                R.id.btnEasy -> {
                    if (isChecked)
                        difficulty = 1
                }
                R.id.btnMid -> {
                    if (isChecked)
                        difficulty = 2

                }
                R.id.btnHard -> {
                    if (isChecked)
                        difficulty = 3
                }
                R.id.btnVeryHard -> {
                    if (isChecked)
                        difficulty = 4
                }
            }
        }

        binding.btnStartGame.addClickListener {
            if (difficulty == -1){
                mainActivity.showCustomToast("난이도를 선택하렴!")
            }else
                mainActivity.navigate(NAVIGATE_TO_GAME, difficulty)
        }

    }
}