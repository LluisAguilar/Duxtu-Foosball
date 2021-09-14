package com.android.code.challenge.foosballranking.ui.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RadioGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.android.code.challenge.foosballranking.R
import com.android.code.challenge.foosballranking.domain.model.Game
import com.android.code.challenge.foosballranking.domain.model.Score
import com.android.code.challenge.foosballranking.ui.adapter.ScoreRecyclerViewAdapter
import kotlinx.android.synthetic.main.fragment_ranking_rab.view.*


class RankingRabFragment : Fragment(), RadioGroup.OnCheckedChangeListener {

    private lateinit var mView:View
    private var mScoresList = arrayListOf<Score>()
    private var mGameList = listOf<Game>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        mView = inflater.inflate(R.layout.fragment_ranking_rab, container, false)

        mView.ranking_recyclerview.layoutManager = LinearLayoutManager(activity, RecyclerView.VERTICAL, false)

        mView.ranking_radio_group.setOnCheckedChangeListener(this)

        return mView
    }

    fun setScoreData(scoresList: List<Game>){
        mGameList = scoresList
        mScoresList = getUserGamesPlayedRanking(scoresList)
        activity?.let {
            mView.radio_games.isChecked = true
            mView.ranking_recyclerview.adapter = ScoreRecyclerViewAdapter(it, getUserGamesPlayedRanking(mGameList))
        }

        if (scoresList.size > 0){
            mView.not_data_tv_ranking.visibility = View.GONE
            mView.ranking_content_layout.visibility = View.VISIBLE
        }

    }

    companion object {

        @JvmStatic
        fun newInstance() = RankingRabFragment()
    }

    private fun mapToList(result: List<MutableMap.MutableEntry<String, Int>>): ArrayList<Score> {
        val scoreList = arrayListOf<Score>()
        for (x in 0 until result.size){
            scoreList.add(Score(result.get(x).key,result.get(x).value))
        }
        return scoreList
    }

    override fun onCheckedChanged(p0: RadioGroup?, p1: Int) {
        when(p1){
            R.id.radio_games -> {
                mScoresList = getUserGamesPlayedRanking(mGameList)
                activity?.let {
                    mView.ranking_recyclerview.adapter = ScoreRecyclerViewAdapter(it, mScoresList)
                }
            }
            R.id.readio_wins -> {
                mScoresList = getUserGamesWonRanking(mGameList)
                activity?.let {
                    mView.ranking_recyclerview.adapter = ScoreRecyclerViewAdapter(it, mScoresList)
                }
            }

        }
    }

    private fun getUserGamesPlayedRanking(gamesList: List<Game>): ArrayList<Score> {
        val userMap = HashMap<String, Int>()

        if (gamesList.size > 0) {
            for (x in 0 until gamesList.size) {

                if (userMap.containsKey(gamesList.get(x).player1)) {
                    val count = userMap.get(gamesList.get(x).player1)!! + 1
                    userMap.put(gamesList.get(x).player1, count)
                } else {
                    userMap.put(gamesList.get(x).player1, 1)
                }

                if (userMap.containsKey(gamesList.get(x).player2)) {
                    val count = userMap.get(gamesList.get(x).player2)!! + 1
                    userMap.put(gamesList.get(x).player2, count)
                } else {
                    userMap.put(gamesList.get(x).player2, 1)
                }
            }
        }

        val result = userMap.entries.sortedByDescending { it.value }
        return  mapToList(result)
    }

    private fun getUserGamesWonRanking(gamesList: List<Game>): ArrayList<Score> {
        val userMap = HashMap<String, Int>()

        if (gamesList.size > 0) {
            for (x in 0 until gamesList.size) {
                if (gamesList.get(x).points1 > gamesList.get(x).points2){
                    if (userMap.containsKey(gamesList.get(x).player1)){
                        val count = userMap.get(gamesList.get(x).player1)!! + 1
                        userMap.put(gamesList.get(x).player1, count)
                    } else {
                        userMap.put(gamesList.get(x).player1, 1)
                    }
                } else if (gamesList.get(x).points1 < gamesList.get(x).points2){
                    if (userMap.containsKey(gamesList.get(x).player2)){
                        val count = userMap.get(gamesList.get(x).player2)!! + 1
                        userMap.put(gamesList.get(x).player2, count)
                    } else {
                        userMap.put(gamesList.get(x).player2, 1)
                    }
                }
            }
        }

        val result = userMap.entries.sortedByDescending { it.value }

        return mapToList(result)
    }

}