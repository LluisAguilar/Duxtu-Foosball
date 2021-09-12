package com.android.code.challenge.foosballranking.ui.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.android.code.challenge.foosballranking.R
import com.android.code.challenge.foosballranking.domain.mapper.GamesEntityToGamesMapper
import com.android.code.challenge.foosballranking.domain.model.Game
import com.android.code.challenge.foosballranking.domain.model.Score
import com.android.code.challenge.foosballranking.domain.viewmodel.GameViewModel

class MainActivity : AppCompatActivity() {

    private lateinit var mGameViewModel: GameViewModel
    private var mGamesEntityToGamesMapper: GamesEntityToGamesMapper? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mGameViewModel = ViewModelProvider(this).get(GameViewModel::class.java)
        mGamesEntityToGamesMapper = GamesEntityToGamesMapper()

        /*mGameViewModel.insertGame(Game("Luis",2, "Juan", 3))
        mGameViewModel.insertGame(Game("Luis",2, "Roberto", 3))
        mGameViewModel.insertGame(Game("Miguel",2, "Juan", 3))
        mGameViewModel.insertGame(Game("Luis",2, "Juan", 3))
        mGameViewModel.insertGame(Game("Luis",2, "Angel", 3))
        mGameViewModel.insertGame(Game("Luis",2, "Jose", 3))
        mGameViewModel.insertGame(Game("Luis",2, "Juan", 3))*/

        mGameViewModel.getAllGames().observe(this, {
            val gamesList = mGamesEntityToGamesMapper?.map(it)
            gamesList?.let {
                getUserGamesWonRanking(it).forEach { println(it.username) }
                println("------------------- Most played--------------------")
                getUserGamesPlayedRanking(it).forEach { println(it.username) }
            }
        })


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

    private fun mapToList(result: List<MutableMap.MutableEntry<String, Int>>): ArrayList<Score> {
        val scoreList = arrayListOf<Score>()
        for (x in 0 until result.size){
            scoreList.add(Score(result.get(x).key,result.get(x).value))
        }
        return scoreList
    }

}