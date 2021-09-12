package com.android.code.challenge.foosballranking.domain.mapper

import com.android.code.challenge.foosballranking.data.db.entity.GameEntity
import com.android.code.challenge.foosballranking.domain.model.Game

class GamesEntityToGamesMapper {


    fun map(gameEntityList: List<GameEntity>): List<Game> {
        val gameList = arrayListOf<Game>()
        gameEntityList.let {
            for (x in 0 until it.size) {
                gameList.add(Game(it.get(x).player1, it.get(x).points1, it.get(x).player2, it.get(x).points2))
            }
        }
        return gameList
    }

    fun reverseMap(value: Game): GameEntity {
        return GameEntity(value.player1, value.points1, value.player2, value.points2)
    }


}