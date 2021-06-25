package com.fatihkurcenli.helloandroid

import androidx.room.Database
import androidx.room.RoomDatabase
import com.fatihkurcenli.helloandroid.dao.ItemEntityDao
import com.fatihkurcenli.helloandroid.model.ItemEntity


@Database(
    entities = [ItemEntity::class],
    version = 1
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun itemEntityDao(): ItemEntityDao
}