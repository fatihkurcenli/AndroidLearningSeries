package com.autumnsun.myholiday.ui.fragment.details

import com.airbnb.epoxy.EpoxyController
import com.autumnsun.myholiday.R
import com.autumnsun.myholiday.databinding.ModelDescriptionBinding
import com.autumnsun.myholiday.databinding.ModelFactBinding
import com.autumnsun.myholiday.databinding.ModelFactsHeaderBinding
import com.autumnsun.myholiday.databinding.ModelMonthsToVisitBinding
import com.autumnsun.myholiday.ui.fragment.epoxy.ViewBindingKotlinModel
import com.fatihkurcenli.myholiday.data.Attraction

class ContentEpoxyController(private val attraction: Attraction) : EpoxyController() {

    var isGridMode: Boolean = false
    lateinit var onChangeLayoutCallBack: () -> Unit

    override fun buildModels() {
        MonthsToVisitEpoxyModel(attraction.months_to_visit).id("months_to_visit").addTo(this)

        DescriptionEpoxyModel(attraction.description).id("description").addTo(this)

        FactsHeaderEpoxyModel(
            "${attraction.facts.size} Facts",
            isGridMode,
            onChangeLayoutCallBack
        ).id("facts_header").addTo(this)

        attraction.facts.forEachIndexed { index, fact ->
            FactEpoxyModel(fact).id("fact_$index").addTo(this)
        }
    }

    data class MonthsToVisitEpoxyModel(
        val monthsToVisit: String
    ) : ViewBindingKotlinModel<ModelMonthsToVisitBinding>(R.layout.model_months_to_visit) {
        override fun ModelMonthsToVisitBinding.bind() {
            monthsToVisitTextView.text = monthsToVisit
        }

        override fun getSpanSize(totalSpanCount: Int, position: Int, itemCount: Int): Int {
            return totalSpanCount
        }
    }

    data class DescriptionEpoxyModel(
        val description: String
    ) : ViewBindingKotlinModel<ModelDescriptionBinding>(R.layout.model_description) {
        override fun ModelDescriptionBinding.bind() {
            descriptionTextView.text = description
        }

        override fun getSpanSize(totalSpanCount: Int, position: Int, itemCount: Int): Int {
            return totalSpanCount
        }
    }

    data class FactsHeaderEpoxyModel(
        val factsText: String,
        val isGridMode: Boolean,
        val onChangeLayoutCallBack: () -> Unit
    ) : ViewBindingKotlinModel<ModelFactsHeaderBinding>(R.layout.model_facts_header) {
        override fun ModelFactsHeaderBinding.bind() {
            modelFactsHeaderTextView.text = factsText
            toggleImageView.setOnClickListener {
                onChangeLayoutCallBack.invoke()
            }
            toggleImageView.setImageResource(if (isGridMode) R.drawable.ic__round_view_list_24 else R.drawable.ic_round_grid_24)
        }

        override fun getSpanSize(totalSpanCount: Int, position: Int, itemCount: Int): Int {
            return totalSpanCount
        }
    }

    data class FactEpoxyModel(
        val fact: String
    ) : ViewBindingKotlinModel<ModelFactBinding>(R.layout.model_fact) {
        override fun ModelFactBinding.bind() {
            factTextView.text = fact
        }

    }
}