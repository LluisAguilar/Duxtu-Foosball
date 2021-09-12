package com.android.code.challenge.foosballranking.data.repositories.repository

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.android.code.challenge.foosballranking.data.db.entity.GameEntity
import com.android.code.challenge.foosballranking.data.repositories.datasource.LocalDataSource

class GameRepository(application: Application) {

    private var mLocalDataSource : LocalDataSource

    init {
        mLocalDataSource = LocalDataSource(application)
    }

    fun insertGame(gameEntity: GameEntity){
        mLocalDataSource.inserGame(gameEntity)
    }

    fun getAllGames(): LiveData<List<GameEntity>> {
        return mLocalDataSource.getGames()
    }

    fun deleteAllGames(){
        return mLocalDataSource.deleteAllGames()
    }


}