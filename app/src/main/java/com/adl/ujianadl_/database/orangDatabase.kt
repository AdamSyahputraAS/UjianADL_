package com.adl.ujianadl_.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.adl.ujianadl_.database.dao.daoOrang
import com.adl.ujianadl_.model.orangModel

@Database(entities = [orangModel::class], version = 1)
abstract class orangDatabase: RoomDatabase() {
    abstract fun daoOrang():daoOrang

    companion object{

        var instance:orangDatabase?=null

        @Synchronized
        fun getInstance(ctx: Context):orangDatabase{

            if(instance == null) {
                instance = Room.databaseBuilder(
                    ctx.applicationContext,
                    orangDatabase::class.java,
                    "orangDatabase"
                )
                    .fallbackToDestructiveMigration()
                    .build()
            }

            return instance!!
        }


    }
}