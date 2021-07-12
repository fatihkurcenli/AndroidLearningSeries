package com.fatihkurcenli.firstapp

import android.app.Application

class TurkeyTeam : Application() {

    companion object {
        lateinit var application: TurkeyTeam
        lateinit var soccerTileList: ArrayList<SoccerTile>
        /**
         * bu sekilde global variable static olarak tutabilir ve her aktiviteden ulasilabilir.
         * Global variable yapmis oluruz bu sekilde Application() nesnesinden miras alarak
         */
    }

    override fun onCreate() {
        super.onCreate()
        application = this
    }
}