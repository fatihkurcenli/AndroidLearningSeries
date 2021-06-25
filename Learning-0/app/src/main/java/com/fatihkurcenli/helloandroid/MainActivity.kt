package com.fatihkurcenli.helloandroid

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.room.Room
import androidx.room.RoomDatabase

class MainActivity : AppCompatActivity() {
    private val appDatabase: RoomDatabase by lazy {
        Room.databaseBuilder(this, AppDatabase::class.java, "to-buy-database").build()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}