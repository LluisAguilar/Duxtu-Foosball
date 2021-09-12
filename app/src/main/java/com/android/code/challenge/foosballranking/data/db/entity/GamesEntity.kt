package com.android.code.challenge.foosballranking.data.db.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Games")
data class GameEntity(
    @ColumnInfo(name = "player_one")
    var player1: String,
    @ColumnInfo(name = "points_one")
    var points1: Int,
    @ColumnInfo(name = "player_two")
    var player2: String,
    @ColumnInfo(name = "points_two")
    var points2: Int
){
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "gameId")
    var gameId: Int? = null
}

