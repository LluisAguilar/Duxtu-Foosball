package com.android.code.challenge.foosballranking.data.db

import android.app.Application
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.android.code.challenge.foosballranking.data.DataUtil.Companion.DUXTU_FOOSBALL_DATABASE_NAME
import com.android.code.challenge.foosballranking.data.db.dao.GamesDao
import com.android.code.challenge.foosballranking.data.db.entity.GameEntity

@Database(entities = arrayOf(GameEntity::class), version = 1, exportSchema = false)
abstract class GamesDB : RoomDatabase() {

    abstract fun gamesDao(): GamesDao

    companion object {
        @Volatile
        private var mInstance: GamesDB? = null

        fun getDatabaseClient(application: Application) : GamesDB {

            if (mInstance == null) {
                synchronized(this) {
                    mInstance = Room
                        .databaseBuilder(application, GamesDB::class.java, DUXTU_FOOSBALL_DATABASE_NAME)
                        .fallbackToDestructiveMigration()
                        .build()
                }
            } else {
                return mInstance!!
            }
            return mInstance!!
        }

    }

}