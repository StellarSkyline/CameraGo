package com.example.camerago.database

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = arrayOf(Pictures::class), version = 1)
abstract class DBHelper: RoomDatabase() {
    abstract fun myDao():DataAccessObject
}