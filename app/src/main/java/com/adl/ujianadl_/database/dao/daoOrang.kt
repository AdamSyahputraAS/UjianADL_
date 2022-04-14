package com.adl.ujianadl_.database.dao

import androidx.room.*
import com.adl.ujianadl_.model.orangModel

@Dao
interface daoOrang {


    @Insert
    fun insertOrang(orang: orangModel)
    @Delete
    fun deleteModel(movie: orangModel)

    @Query("SELECT * FROM orangModel")
    fun getAll(): List<orangModel>

}