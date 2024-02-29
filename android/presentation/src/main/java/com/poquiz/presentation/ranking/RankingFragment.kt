package com.poquiz.presentation.ranking

import android.graphics.Color
import android.os.Bundle
import android.util.TypedValue
import android.view.Gravity
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import android.widget.ListView
import android.widget.TextView
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.viewModels
import com.poquiz.domain.model.Rank
import com.poquiz.presentation.MainActivity
import com.poquiz.presentation.R
import com.poquiz.presentation.databinding.FragmentRankingBinding
import com.ramotion.expandingcollection.ECBackgroundSwitcherView
import com.ramotion.expandingcollection.ECCardData
import com.ramotion.expandingcollection.ECPagerView
import com.ramotion.expandingcollection.ECPagerViewAdapter
import com.ssafy.template.board.config.BaseFragment
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class RankingFragment : BaseFragment<FragmentRankingBinding>(
    FragmentRankingBinding::bind, R.layout.fragment_ranking)
{
    private val viewModel: RankingViewModel by viewModels()
    private lateinit var mainActivity: MainActivity

    private var ecPagerView: ECPagerView? = null

    private var backPressedTime = 0L
    private val onBackPressedCallback = object : OnBackPressedCallback(true) {
        override fun handleOnBackPressed() {
            if (!ecPagerView!!.collapse()) {
                if (System.currentTimeMillis() - backPressedTime <= 2000) {
                    mainActivity.finish()
                } else {
                    backPressedTime = System.currentTimeMillis()
                    mainActivity.showCustomToast("한번 더 누르면 종료 된단다!")
                }
            }
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mainActivity = _activity as MainActivity
        viewModel.loadAllRank()

        registerObserver()

        // Get pager from layout
        ecPagerView = binding.ecPagerElement
        mainActivity.onBackPressedDispatcher.addCallback(onBackPressedCallback)

    }

    private fun registerObserver(){
        viewModel.loadFinished.observe(viewLifecycleOwner){
            if (it){
                val dataset: MutableList<ECCardData<Rank>> = ArrayList()
                dataset.add(
                    CardDataImpl(
                        "",
                        R.drawable.bg1,
                        R.drawable.ranking_easy,
                        viewModel.lowRank.value
                    )
                )
                dataset.add(
                    CardDataImpl(
                        "",
                        R.drawable.bg2,
                        R.drawable.ranking_mid,
                        viewModel.normalRank.value
                    )
                )
                dataset.add(
                    CardDataImpl(
                        "",
                        R.drawable.bg3,
                        R.drawable.ranking_hard,
                        viewModel.highRank.value
                    )
                )
                dataset.add(
                    CardDataImpl(
                        "",
                        R.drawable.bg4,
                        R.drawable.ranking_master,
                        viewModel.highestRank.value
                    )
                )

                dataset


                ecPagerView!!.setPagerViewAdapter(object : ECPagerViewAdapter(requireContext(),
                    dataset as List<ECCardData<Any>>?
                ) {
                    override fun instantiateCard(
                        inflaterService: LayoutInflater,
                        head: ViewGroup,
                        list: ListView,
                        data: ECCardData<*>
                    ) {
                        // Data object for current card
                        val cardData = data as CardDataImpl

                        // Set adapter and items to current card content list
                        list.adapter = CardListItemAdapter(requireContext(), cardData.listItems)
                        // Also some visual tuning can be done here
                        list.setBackgroundColor(Color.WHITE)

                        // Here we can create elements for head view or inflate layout from xml using inflater service
                        val cardTitle = TextView(requireContext())
                        cardTitle.setText(cardData.getCardTitle())
                        cardTitle.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 20f)
                        val layoutParams = FrameLayout.LayoutParams(
                            FrameLayout.LayoutParams.WRAP_CONTENT,
                            FrameLayout.LayoutParams.WRAP_CONTENT
                        )
                        layoutParams.gravity = Gravity.CENTER
                        head.addView(cardTitle, layoutParams)

                        // Card toggling by click on head element
                        head.setOnClickListener { ecPagerView!!.toggle() }
                    }
                })

                // Add background switcher to pager view
                ecPagerView!!.setBackgroundSwitcherView(binding.ecBgSwitcherElement)
            }
        }
    }
}