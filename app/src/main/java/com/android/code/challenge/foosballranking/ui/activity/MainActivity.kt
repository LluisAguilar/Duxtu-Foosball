package com.android.code.challenge.foosballranking.ui.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.viewpager2.widget.ViewPager2
import com.android.code.challenge.foosballranking.R
import com.android.code.challenge.foosballranking.domain.mapper.GamesEntityToGamesMapper
import com.android.code.challenge.foosballranking.domain.viewmodel.GameViewModel
import com.android.code.challenge.foosballranking.ui.adapter.FragmentViewPagerAdapter
import com.android.code.challenge.foosballranking.ui.fragment.AddGameFragment
import com.android.code.challenge.foosballranking.ui.fragment.GamesTabFragment
import com.android.code.challenge.foosballranking.ui.fragment.RankingRabFragment
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), TabLayoutMediator.TabConfigurationStrategy{

    private lateinit var mGameViewModel: GameViewModel
    private var mGamesEntityToGamesMapper: GamesEntityToGamesMapper? = null

    // Fragment for tab menu
    private val gamesTabFragment = GamesTabFragment.newInstance()
    private val rankingRabFragment = RankingRabFragment.newInstance()
    private val addGameFragment = AddGameFragment.newInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        scores_viewpager.adapter =  FragmentViewPagerAdapter(arrayListOf(gamesTabFragment, rankingRabFragment, addGameFragment), this)

        TabLayoutMediator(tab_layout, scores_viewpager, this).attach()

        mGameViewModel = ViewModelProvider(this).get(GameViewModel::class.java)
        mGamesEntityToGamesMapper = GamesEntityToGamesMapper()

        scores_viewpager.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                callGamesData()
                super.onPageSelected(position)
            }
        })

    }

    override fun onResume() {
        callGamesData()

        super.onResume()
    }

    private fun callGamesData() {
        mGameViewModel.getAllGames().observe(this, {
            val gamesList = mGamesEntityToGamesMapper?.map(it)
            gamesList?.let {
                gamesTabFragment.setGameData(it)
                rankingRabFragment.setScoreData(it)
            }
        })
    }

    override fun onConfigureTab(tab: TabLayout.Tab, position: Int) {
        when(position) {

            0 -> {
                tab.text = getString(R.string.games)
            }
            1 -> {
                tab.text = getString(R.string.ranking)
            }
            2 -> {
                tab.text = getString(R.string.add_games)
            }

        }
    }

}