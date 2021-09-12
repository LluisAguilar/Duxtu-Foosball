package com.android.code.challenge.foosballranking.domain.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.android.code.challenge.foosballranking.data.db.entity.GameEntity
import com.android.code.challenge.foosballranking.data.repositories.repository.GameRepository
import com.android.code.challenge.foosballranking.domain.mapper.GamesEntityToGamesMapper
import com.android.code.challenge.foosballranking.domain.model.Game

class GameViewModel(application: Application) : AndroidViewModel(application) {

    private val mGameRepository: GameRepository
    private val mGamesEntityToGamesMapper : GamesEntityToGamesMapper

    init {
        mGameRepository = GameRepository(application)
        mGamesEntityToGamesMapper = GamesEntityToGamesMapper()
    }

    fun insertGame(game:Game){
        mGameRepository.insertGame(mGamesEntityToGamesMapper.reverseMap(game))
    }

    fun getAllGames() : LiveData<List<GameEntity>> {
        return mGameRepository.getAllGames()
    }

}