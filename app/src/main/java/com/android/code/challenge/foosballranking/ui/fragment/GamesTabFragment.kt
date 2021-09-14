package com.android.code.challenge.foosballranking.ui.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView.VERTICAL
import com.android.code.challenge.foosballranking.R
import com.android.code.challenge.foosballranking.domain.model.Game
import com.android.code.challenge.foosballranking.ui.adapter.GamesRecyclerViewAdapter
import kotlinx.android.synthetic.main.fragment_games_tab.*
import kotlinx.android.synthetic.main.fragment_games_tab.view.*

class GamesTabFragment : Fragment() {

    private lateinit var mView:View

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {
        // Inflate the layout for this fragment
        mView = inflater.inflate(R.layout.fragment_games_tab, container, false)

        mView.games_recyclerview.layoutManager = LinearLayoutManager(activity, VERTICAL, false)

        return mView
    }

    fun setGameData(gameList: List<Game>) {
        activity?.let {
            mView.games_recyclerview.adapter = GamesRecyclerViewAdapter(it, gameList as ArrayList<Game>)
        }

        if (gameList.size > 0){
            mView.not_data_tv.visibility = GONE
            mView.games_recyclerview.visibility = VISIBLE
        }
    }


    companion object {
        @JvmStatic
        fun newInstance() = GamesTabFragment()
    }
}