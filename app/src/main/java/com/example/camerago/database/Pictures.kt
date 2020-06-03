package com.example.camerago.database

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "photo_table")
data class Pictures(
    @PrimaryKey(autoGenerate = true)
    var id:Int = 0,
    var photoString:String =""
)