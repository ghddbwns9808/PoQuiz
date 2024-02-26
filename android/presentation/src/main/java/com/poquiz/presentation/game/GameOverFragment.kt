package com.poquiz.presentation.game

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.bumptech.glide.Glide
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.poquiz.domain.model.Rank
import com.poquiz.presentation.MainActivity
import com.poquiz.presentation.R
import com.poquiz.presentation.base.Constants
import com.poquiz.presentation.databinding.FragmentGameOverBinding
import com.ssafy.template.board.config.BaseFragment
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

private const val TAG = "GameOverFragment_hong"

@AndroidEntryPoint
class GameOverFragment : BaseFragment<FragmentGameOverBinding>(
    FragmentGameOverBinding::bind, R.layout.fragment_game_over
) {
    private val viewModel: GameOverViewModel by viewModels()
    private var difficulty: Int = -1
    private var correct: Int = -1

    private lateinit var mainActivity: MainActivity

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            difficulty = it.getInt("difficulty")
            correct = it.getInt("correct")
        }
        mainActivity = _activity as MainActivity
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mainActivity.findViewById<BottomNavigationView>(R.id.btmNav).visibility = View.GONE

        registerObserver()
        registerRanking()

        Glide
            .with(this)
            .load(R.raw.moving_ditto)
            .into(binding.ivDitto)

        binding.tvCorrect.text = correct.toString()

        binding.btnToMain.addClickListener {
            mainActivity.navigate(Constants.NAVIGATE_TO_GAME_START)
        }

    }

    private fun registerObserver(){
        viewModel.rankUpdateResult.observe(viewLifecycleOwner){
            mainActivity.showCustomToast(it)
        }
    }

    private fun registerRanking(){
        val pref = requireContext().getSharedPreferences("user_info", Context.MODE_PRIVATE)
        val id = pref.getString("id", "-1")
        val nickname = pref.getString("nickname", "-1")

        Log.d(TAG, "registerRanking: ${id}, correct: ${correct}")

        when(difficulty){
            1 -> {
                viewModel.updateLowRank(Rank(nickname!!, correct.toString(), System.currentTimeMillis()))
            }
            2 -> {
                viewModel.updateNormalRank(Rank(nickname!!, correct.toString(), System.currentTimeMillis()))
            }
            3 -> {
                viewModel.updateHighRank(Rank(nickname!!, correct.toString(), System.currentTimeMillis()))
            }
            4 -> {
                viewModel.updateHighestRank(Rank(nickname!!, correct.toString(), System.currentTimeMillis()))
            }
        }
    }

    companion object {
        @JvmStatic
        fun newInstance(p1: Int, p2: Int) =
            GameOverFragment().apply {
                arguments = Bundle().apply {
                    putInt("difficulty", p1)
                    putInt("correct", p2)
                }
            }
    }
}