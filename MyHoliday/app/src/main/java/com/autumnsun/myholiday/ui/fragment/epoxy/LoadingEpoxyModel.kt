package com.autumnsun.myholiday.ui.fragment.epoxy

import com.autumnsun.myholiday.R
import com.autumnsun.myholiday.databinding.EpoxyModelLoadingBinding

class LoadingEpoxyModel :
    ViewBindingKotlinModel<EpoxyModelLoadingBinding>(R.layout.epoxy_model_loading) {
    override fun EpoxyModelLoadingBinding.bind() {
        //noting to do just showing circleprogress
    }
}