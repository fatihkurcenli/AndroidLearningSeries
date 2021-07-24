package com.fatihkurcenli.myholiday.ui.fragment.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.fatihkurcenli.myholiday.R
import com.fatihkurcenli.myholiday.data.Attraction
import com.fatihkurcenli.myholiday.databinding.ViewHolderAttractionBinding
import com.squareup.picasso.Picasso

class HomeFragmentAdapter(private val onClickedCallBack: (String) -> Unit) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private val attractions = ArrayList<Attraction>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return AttractionViewHolder(parent)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as AttractionViewHolder).onBind(attractions[position], onClickedCallBack)
    }

    override fun getItemCount(): Int = attractions.size

    fun setData(attraction: List<Attraction>) {
        this.attractions.clear()
        this.attractions.addAll(attraction)
    }

    inner class AttractionViewHolder(parent: ViewGroup) :
        RecyclerView.ViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.view_holder_attraction, parent, false
            )
        ) {
        private val binding = ViewHolderAttractionBinding.bind(itemView)

        fun onBind(attraction: Attraction, onClicked: (String) -> Unit) {
            binding.titleTextView.text = attraction.title
            Picasso.get().load(attraction.image_url).into(binding.headerImageView)
            binding.monthsToVisitTextView.text = attraction.months_to_visit
            binding.root.setOnClickListener {
                onClicked(attraction.id)
            }
        }
    }
}