package com.fatihkurcenli.myholiday.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.navArgs
import com.fatihkurcenli.myholiday.data.Attraction
import com.fatihkurcenli.myholiday.databinding.FragmentAttractionDetailBinding
import com.squareup.picasso.Picasso

class AttractionDetailFragment : BaseFragment() {

    private var _binding: FragmentAttractionDetailBinding? = null
    private val binding get() = _binding!!
    private val safeArgs: AttractionDetailFragmentArgs by navArgs()
    private val attraction: Attraction by lazy {
        attractions.find { it.id == safeArgs.attractionId }!!
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentAttractionDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.numberOfFactsTextView.text = "${attraction.facts.size} Facts"
        binding.descriptionTextView.text = attraction.description
        binding.monthsToVisitTextView.text = attraction.months_to_visit
        Picasso.get().load(attraction.image_url).into(binding.headerImageView)
        binding.titleTextView.text = attraction.title
    }


    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}