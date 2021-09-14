package com.android.code.challenge.foosballranking.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.android.code.challenge.foosballranking.R
import com.android.code.challenge.foosballranking.domain.model.Score

class ScoreRecyclerViewAdapter(val context: Context, var scoresList: ArrayList<Score>) :
    RecyclerView.Adapter<ScoreRecyclerViewAdapter.ScoresListViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ScoresListViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_scores, parent, false)
        return ScoresListViewHolder(view)
    }

    override fun onBindViewHolder(holder: ScoresListViewHolder, position: Int) {
        holder.bind(context, scoresList.get(position))
    }

    override fun getItemCount(): Int {
        return scoresList.size
    }

    fun updateScoreData(scoresList: ArrayList<Score>) {
        this.scoresList = scoresList
        notifyDataSetChanged()
    }


    class ScoresListViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        private val mPlayer: TextView = view.findViewById(R.id.item_player_name)
        private val mgames: TextView = view.findViewById(R.id.item_player_games)

        fun bind(context: Context, score: Score) {
            mPlayer.text = score.username
            mgames.text = score.score.toString()
        }

    }

}