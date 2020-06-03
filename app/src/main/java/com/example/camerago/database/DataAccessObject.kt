package com.example.camerago.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface DataAccessObject {
    @Insert
    fun addPicture(photo:Pictures)

    @Query("select * from photo_table")
    fun getPictureList():List<Pictures>

    @Query("Delete from photo_table")
    fun deleteData()
}