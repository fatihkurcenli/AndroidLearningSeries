package com.fatihkurcenli.myholiday.arch

import android.content.Context
import com.autumnsun.myholiday.R
import com.fatihkurcenli.myholiday.data.Attraction
import com.fatihkurcenli.myholiday.data.AttractionResponse
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory

class AttractionsRepository {
    //networking call repository.
    private val moshi = Moshi.Builder().addLast(KotlinJsonAdapterFactory()).build()

    fun parseAttractions(context: Context): ArrayList<Attraction> {
        val textFormFile =
            context.resources.openRawResource(R.raw.croatia).bufferedReader().use { it.readText() }
        val adapter: JsonAdapter<AttractionResponse> = moshi.adapter(AttractionResponse::class.java)
        return adapter.fromJson(textFormFile)!!.attractions as ArrayList<Attraction>
    }
}