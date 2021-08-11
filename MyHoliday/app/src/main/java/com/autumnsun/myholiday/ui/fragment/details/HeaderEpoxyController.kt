package com.autumnsun.myholiday.ui.fragment.details

import com.airbnb.epoxy.EpoxyController
import com.autumnsun.myholiday.R
import com.autumnsun.myholiday.databinding.ModelHeaderImageBinding
import com.autumnsun.myholiday.ui.fragment.epoxy.ViewBindingKotlinModel
import com.squareup.picasso.Picasso

class HeaderEpoxyController(val imageUrls: List<String>) : EpoxyController() {

    override fun buildModels() {
        imageUrls.forEachIndexed { index, url -> //give us index for unique id.
            HeaderImageEpoxyModel(url).id(index).addTo(this)
        }
    }

    inner class HeaderImageEpoxyModel(
        val imageUrl: String
    ) : ViewBindingKotlinModel<ModelHeaderImageBinding>(R.layout.model_header_image) {
        override fun ModelHeaderImageBinding.bind() {
            Picasso.get().load(imageUrl).into(imageView)
        }

    }
}