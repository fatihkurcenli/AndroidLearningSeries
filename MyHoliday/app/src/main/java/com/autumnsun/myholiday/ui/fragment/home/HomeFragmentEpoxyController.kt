package com.fatihkurcenli.myholiday.ui.fragment.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.airbnb.epoxy.EpoxyController
import com.autumnsun.myholiday.R
import com.autumnsun.myholiday.databinding.EpoxyModelHeaderBinding
import com.autumnsun.myholiday.databinding.ViewHolderAttractionBinding
import com.autumnsun.myholiday.ui.fragment.epoxy.LoadingEpoxyModel
import com.autumnsun.myholiday.ui.fragment.epoxy.ViewBindingKotlinModel
import com.fatihkurcenli.myholiday.data.Attraction
import com.squareup.picasso.Picasso

class HomeFragmentEpoxyController(private val onClickedCallBack: (String) -> Unit) :
    EpoxyController() {

    var isLoading: Boolean = false
        set(value) {
            field = value
            if (field) {
                requestModelBuild()
            }
        }


    var attractions = ArrayList<Attraction>()
        set(value) {
            field = value
            isLoading = false
            requestModelBuild()
        }


    override fun buildModels() {
        if (isLoading) {
            LoadingEpoxyModel().id("loading_state").addTo(this)
            return
        }

        if (attractions.isEmpty()) {
            //todo empty state
            return
        }
        val firstGroup =
            attractions.filter { it.title.startsWith("s", true) || it.title.startsWith("D", false) }

        HeaderEpoxyModel("Recently Viewed").id("header_1").addTo(this)
        firstGroup.forEach { attraction ->
            AttractionEpoxyModel(attraction, onClickedCallBack).id(attraction.id).addTo(this)
        }

        HeaderEpoxyModel("All attractions").id("header_1").addTo(this)
        attractions.forEach { attraction ->
            AttractionEpoxyModel(attraction, onClickedCallBack).id(attraction.id).addTo(this)
        }
    }


    //Epoxy model airbnb's epoxy
    data class AttractionEpoxyModel(
        val attraction: Attraction,
        val onClicked: (String) -> Unit
    ) : ViewBindingKotlinModel<ViewHolderAttractionBinding>(R.layout.view_holder_attraction) {
        override fun ViewHolderAttractionBinding.bind() {
            titleTextView.text = attraction.title
            Picasso.get().load(attraction.image_url).into(headerImageView)
            monthsToVisitTextView.text = attraction.months_to_visit
            root.setOnClickListener {
                onClicked(attraction.id)
            }
        }

    }

    data class HeaderEpoxyModel(
        val headerText: String
    ) : ViewBindingKotlinModel<EpoxyModelHeaderBinding>(R.layout.epoxy_model_header) {
        override fun EpoxyModelHeaderBinding.bind() {
            headerTextView.text = headerText
        }

    }


}