package com.fatihkurcenli.myholiday

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import com.fatihkurcenli.myholiday.data.Attraction
import com.fatihkurcenli.myholiday.data.AttractionResponse
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory

class MainActivity : AppCompatActivity() {
    lateinit var navController: NavController
    private val moshi = Moshi.Builder().addLast(KotlinJsonAdapterFactory()).build()
    val attractionList: List<Attraction> by lazy {
        parseAttractions()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.navHostFragment) as NavHostFragment
        navController = navHostFragment.navController


    }

    private fun parseAttractions(): List<Attraction> {
        val textFormFile =
            resources.openRawResource(R.raw.croatia).bufferedReader().use { it.readText() }
        val adapter: JsonAdapter<AttractionResponse> = moshi.adapter(AttractionResponse::class.java)
        return adapter.fromJson(textFormFile)!!.attractions
    }
}