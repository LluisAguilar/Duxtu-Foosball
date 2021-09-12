package com.android.code.challenge.foosballranking.data.db.dao

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.android.code.challenge.foosballranking.data.db.entity.GameEntity

@Dao
interface GamesDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertGame(gameEntity: GameEntity)

    @Query("SELECT * FROM Games")
    fun getGames() : LiveData<List<GameEntity>>

    @Query("DELETE FROM Games")
    suspend fun deleteGames()
}