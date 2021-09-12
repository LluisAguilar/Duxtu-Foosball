package com.android.code.challenge.foosballranking.data.repositories.datasource

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.android.code.challenge.foosballranking.data.db.GamesDB
import com.android.code.challenge.foosballranking.data.db.dao.GamesDao
import com.android.code.challenge.foosballranking.data.db.entity.GameEntity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.launch

class LocalDataSource (application: Application) {

    private var mGamesDB : GamesDB
    private var mGamesDao : GamesDao

    init {
        mGamesDB = GamesDB.getDatabaseClient(application)
        mGamesDao = mGamesDB.gamesDao()
    }

    fun inserGame(gameEntity: GameEntity){
        CoroutineScope(IO).launch {
            mGamesDao.insertGame(gameEntity)
        }
    }

    fun getGames(): LiveData<List<GameEntity>> {
        return mGamesDao.getGames()
    }

    fun deleteAllGames(){
        CoroutineScope(IO).launch {
            mGamesDao.deleteGames()
        }
    }
}