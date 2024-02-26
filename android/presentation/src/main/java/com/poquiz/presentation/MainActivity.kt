package com.poquiz.presentation

import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.FragmentManager
import com.poquiz.presentation.base.Constants
import com.poquiz.presentation.base.Constants.NAVIGATE_TO_GAME
import com.poquiz.presentation.base.Constants.NAVIGATE_TO_GAME_OVER
import com.poquiz.presentation.base.Constants.NAVIGATE_TO_GAME_START
import com.poquiz.presentation.databinding.ActivityMainBinding
import com.poquiz.presentation.game.GameFragment
import com.poquiz.presentation.game.GameOverFragment
import com.poquiz.presentation.game.GameStartFragment
import com.poquiz.presentation.ranking.RankingFragment
import com.ssafy.template.board.config.BaseActivity
import dagger.hilt.android.AndroidEntryPoint

private const val TAG = "MainActivity_hong"
@AndroidEntryPoint
class MainActivity : BaseActivity<ActivityMainBinding>(
    ActivityMainBinding::inflate
) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        initBottomNavigation()

        val pref = getSharedPreferences("user_info", Context.MODE_PRIVATE)
        showCustomToast("환영한다 ${pref.getString("nickname", "닉네임")}!")

    }

    private fun initBottomNavigation() {

        supportFragmentManager.beginTransaction()
            .replace(R.id.frag_main, GameStartFragment())
            .commitAllowingStateLoss()

        binding.btmNav.setOnItemSelectedListener { item ->
            when(item.itemId){
                R.id.game -> {
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.frag_main, GameStartFragment())
                        .commitAllowingStateLoss()
                }
                R.id.ranking -> {
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.frag_main, RankingFragment())
                        .commitAllowingStateLoss()
                }
            }
            true
        }
    }

    fun navigate(id: Int, difficulty: Int = -1, correct: Int = -1){
        when(id){
            NAVIGATE_TO_GAME_START -> {
                supportFragmentManager.beginTransaction()
                    .replace(R.id.frag_main, GameStartFragment())
                    .commitAllowingStateLoss()
            }

            NAVIGATE_TO_GAME -> {
                supportFragmentManager.beginTransaction()
                    .replace(R.id.frag_main, GameFragment.newInstance(difficulty))
                    .addToBackStack("game")
                    .commitAllowingStateLoss()
            }

            NAVIGATE_TO_GAME_OVER -> {
                Log.d(TAG, "navigate: ")
                supportFragmentManager.popBackStack("game", FragmentManager.POP_BACK_STACK_INCLUSIVE)
                supportFragmentManager.beginTransaction()
                    .replace(R.id.frag_main, GameOverFragment.newInstance(difficulty, correct))
                    .commitAllowingStateLoss()
            }
        }
    }
}