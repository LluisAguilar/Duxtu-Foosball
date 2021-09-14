package com.android.code.challenge.foosballranking.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.android.code.challenge.foosballranking.R
import com.android.code.challenge.foosballranking.domain.model.Game

class GamesRecyclerViewAdapter(val context: Context, var gamesList: ArrayList<Game>) :
    RecyclerView.Adapter<GamesRecyclerViewAdapter.GamesListViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GamesListViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_games, parent, false)
        return GamesListViewHolder(view)
    }

    override fun onBindViewHolder(holder: GamesListViewHolder, position: Int) {
        holder.bind(context, gamesList.get(position))
    }

    override fun getItemCount(): Int {
        return gamesList.size
    }

    fun updateGameData(gamesList: ArrayList<Game>) {
        this.gamesList = gamesList
        notifyDataSetChanged()
    }


    class GamesListViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        private val mGameNumber: TextView = view.findViewById(R.id.game_number)
        private val mPlayer1: TextView = view.findViewById(R.id.item_player_1)
        private val mPlayer2: TextView = view.findViewById(R.id.item_player_2)
        private val mScore1: TextView = view.findViewById(R.id.item_score_player_1)
        private val mScore2: TextView = view.findViewById(R.id.item_score_player_2)
        private val mWinner: TextView = view.findViewById(R.id.item_winner_name)
        private val mWinnerTitle: TextView = view.findViewById(R.id.item_winner_title)

        fun bind(context: Context, games: Game) {
            mGameNumber.text = String.format(context.getString(R.string.game) + " " + (adapterPosition + 1))
            mPlayer1.text = games.player1
            mPlayer2.text = games.player2
            mScore1.text = games.points1.toString()
            mScore2.text = games.points2.toString()

            if (games.points1 > games.points2) {
                mWinner.text = games.player1
            } else if (games.points1 < games.points2) {
                mWinner.text = games.player2
            } else {
                mWinnerTitle.visibility = View.GONE
                mWinner.text = context.getString(R.string.tie)
            }

        }

    }

}