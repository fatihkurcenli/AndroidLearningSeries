package com.fatihkurcenli.firstapp

import android.app.Application

class TSLApplication : Application() {

    companion object {
        lateinit var application: TSLApplication
    }

    override fun onCreate() {
        super.onCreate()
        application = this
        SharedPrefUtil.init(this)
    }

}