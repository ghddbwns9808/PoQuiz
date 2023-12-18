package com.poquiz.presentation.game

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.view.KeyEvent
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import android.view.inputmethod.InputMethodManager
import android.widget.LinearLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.marginRight
import androidx.fragment.app.viewModels
import com.appolica.flubber.Flubber
import com.bumptech.glide.Glide
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.poquiz.presentation.MainActivity
import com.poquiz.presentation.R
import com.poquiz.presentation.base.Constants
import com.poquiz.presentation.databinding.FragmentGameBinding
import com.ssafy.template.board.config.BaseFragment
import dagger.hilt.android.AndroidEntryPoint
import java.util.Random

@AndroidEntryPoint
class GameFragment : BaseFragment<FragmentGameBinding>(
    FragmentGameBinding::bind, R.layout.fragment_game
) {
    private var difficulty: Int = -1
    private lateinit var mainActivity: MainActivity
    private lateinit var remainNumbers : ArrayList<Int>
    private val viewModel: GameViewModel by viewModels()

    private var remainCnt = 100

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            difficulty = it.getInt("difficulty")
            remainNumbers = arrayListOf()
            for (i in 100*(difficulty-1) + 1 .. 100*(difficulty))
                remainNumbers.add(i)
        }
        mainActivity = _activity as MainActivity
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mainActivity.findViewById<BottomNavigationView>(R.id.btmNav).visibility = View.GONE

        binding.etName.editText!!.setOnKeyListener { view, i, keyEvent ->
            if ((keyEvent.action == KeyEvent.ACTION_DOWN) && (i == KeyEvent.KEYCODE_ENTER)) {
                hideKeyboard()
                binding.btnEnter.click()
                true
            }else
                false
        }

        registerListener()
        registerObserver()

        playGame()
    }


    private fun playGame(){
        binding.etName.editText!!.text.clear()
        binding.tvPokemonNo.visibility = View.GONE
        binding.layoutPokeInfo.visibility = View.GONE
        binding.btnEnter.visibility = View.VISIBLE

        if (remainCnt == 0){
            viewModel.endGame()
        }else{
            val randomIdx = Random().nextInt(remainCnt)
            val id = remainNumbers[randomIdx]
            remainNumbers.removeAt(randomIdx)
            remainCnt--

            viewModel.loadPokeInfo(id)
        }
    }

    private fun registerListener(){
        val vibration = AnimationUtils.loadAnimation(requireContext(), R.anim.edit_text_vibration)
        binding.btnEnter.addClickListener {
            if (binding.etName.editText!!.text.trim().toString().isBlank()){
                binding.etName.startAnimation(vibration)
            }else{
                viewModel.stopTimer()
                binding.btnEnter.visibility = View.GONE
                binding.tvPokemonNo.visibility = View.VISIBLE
                binding.layoutPokeInfo.visibility = View.VISIBLE

                //정답일 때
                if (binding.etName.editText!!.text.trim().toString() == viewModel.quiz.value!!.name.trim()){
                    viewModel.increaseCorrectCnt()
                    animateDoctor(1)
                    Handler(Looper.getMainLooper()).postDelayed({
                        playGame()
                    }, 3500)

                }else{
                    animateDoctor(2)
                    Handler(Looper.getMainLooper()).postDelayed({
                        viewModel.endGame()
                    }, 3500)
                }
            }
        }
    }

    private fun registerObserver(){
        viewModel.remainTime.observe(viewLifecycleOwner){
            binding.tvRemainTime.text = "$it 초"
            Log.d(TAG, "registerObserver: time : $it")
        }

        viewModel.correctCnt.observe(viewLifecycleOwner){
            binding.tvCorrect.text = "맞은문제 $it"
            Log.d(TAG, "registerObserver: 맞은문제: $it")
        }

        viewModel.isEnd.observe(viewLifecycleOwner){
            // 게임 종료됨
            if (it){
                mainActivity.navigate(Constants.NAVIGATE_TO_GAME_OVER, difficulty, viewModel.correctCnt.value!!)
            }
        }

        viewModel.isTimeOut.observe(viewLifecycleOwner){
            if (it){
                animateDoctor(3)

                binding.tvRemainTime.text = "0 초"
                binding.btnEnter.visibility = View.GONE
                binding.tvPokemonNo.visibility = View.VISIBLE
                binding.layoutPokeInfo.visibility = View.VISIBLE

                Handler(Looper.getMainLooper()).postDelayed({
                    mainActivity.navigate(Constants.NAVIGATE_TO_GAME_OVER, difficulty, viewModel.correctCnt.value!!)
                }, 3000)
            }
        }

        viewModel.quiz.observe(viewLifecycleOwner){
            Log.d(TAG, "registerObserver: pk: ${it.name}, ${it.id}")
            Glide
                .with(requireContext())
                .load(it.img)

                .into(binding.ivPokemon)
            if (it.id < 10)
                binding.tvPokemonNo.text = "No.0000${it.id}"
            else if (it.id in 10 until 100)
                binding.tvPokemonNo.text = "No.000${it.id}"
            else
                binding.tvPokemonNo.text = "No.00${it.id}"

            binding.tvPokemonName.text = it.name
            binding.tvPokemonType.text = it.type

        }
    }

    private fun animateDoctor(mode: Int){
        var view: View? = null
        when(mode){
            1 -> view = binding.ivDoctorOhCorrect
            2 -> view = binding.ivDoctorOhWrong
            3 -> view = binding.ivDoctorOhTimeout
        }

        view!!.visibility = View.VISIBLE

        Flubber.with()
            .animation(Flubber.AnimationPreset.SLIDE_RIGHT)
            .duration(1000)
            .createFor(view)
            .start()


        Flubber.with()
            .animation(Flubber.AnimationPreset.SLIDE_OUT_LEFT)
            .duration(1000)
            .delay(2000)
            .createFor(view)
            .start()
    }

    companion object {
        @JvmStatic
        fun newInstance(p1: Int) =
            GameFragment().apply {
                arguments = Bundle().apply {
                    putInt("difficulty", p1)
                }
            }
    }
}