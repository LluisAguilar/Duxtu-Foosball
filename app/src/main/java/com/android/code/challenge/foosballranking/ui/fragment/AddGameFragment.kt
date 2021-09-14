package com.android.code.challenge.foosballranking.ui.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isEmpty
import com.android.code.challenge.foosballranking.R
import com.android.code.challenge.foosballranking.domain.model.Game
import com.android.code.challenge.foosballranking.ui.activity.MainActivity
import kotlinx.android.synthetic.main.fragment_add_game.*
import kotlinx.android.synthetic.main.fragment_add_game.view.*

class AddGameFragment : Fragment() {

    private lateinit var mView: View

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment

        mView = inflater.inflate(R.layout.fragment_add_game, container, false)

        mView.add_game_btn.setOnClickListener {

            activity?.let {
                if (!mView.player_1_tv.text.isEmpty() && !mView.player_1_score_tv.text.isEmpty() && !mView.player_2_tv.text.isEmpty() && !mView.player_2_score_tv.text.isEmpty()) {
                    (it as MainActivity).saveGame(Game(mView.player_1_tv.text.toString(), mView.player_1_score_tv.text.toString().toInt(), mView.player_2_tv.text.toString(), mView.player_2_score_tv.text.toString().toInt()))

                    Toast.makeText(it, getString(R.string.added_successfully), Toast.LENGTH_SHORT).show()
                    clearFields()
                } else {
                    Toast.makeText(it, getString(R.string.empty_fields), Toast.LENGTH_SHORT).show()
                }
            }

        }


        return mView
    }

    private fun clearFields() {
        mView.player_1_tv.setText("")
        mView.player_1_score_tv.setText("")
        mView.player_2_tv.setText("")
        mView.player_2_score_tv.setText("")
    }

    companion object {

        @JvmStatic
        fun newInstance() = AddGameFragment()
    }
}