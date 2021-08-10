package com.fatihkurcenli.myholiday.arch

import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.fatihkurcenli.myholiday.data.Attraction

class AttractionsViewModel : ViewModel() {

    private val repository = AttractionsRepository()

    fun init(context: Context) {
        val attractionList = repository.parseAttractions(context)
        attractionsListLiveData.postValue(attractionList)

    }

    //AttractionListLiveData from Attraction
    //HomeFragment data
    val attractionsListLiveData = MutableLiveData<List<Attraction>>()

    //AttractionDetailFragment
    val selectedAttractionLiveData = MutableLiveData<Attraction>()

    //Find the attraction list of the id on live data and post the selectedAttractionLiveData
    //Eger delay koyarsak tıklanildiginda gecikmeli olarak acilcak bundan dolayi eski data durucaktir
    fun onAttractionSelected(attractionId: String) {
        val attraction = attractionsListLiveData.value?.find {
            it.id == attractionId
        } ?: return
        selectedAttractionLiveData.postValue(attraction)
    }
}