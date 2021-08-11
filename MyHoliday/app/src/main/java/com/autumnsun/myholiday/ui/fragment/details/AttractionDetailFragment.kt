package com.autumnsun.myholiday.ui.fragment.details

import android.os.Bundle
import android.view.*
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.LinearSnapHelper
import com.autumnsun.myholiday.R
import com.autumnsun.myholiday.databinding.FragmentAttractionDetailBinding
import com.fatihkurcenli.myholiday.ui.fragment.BaseFragment
import com.squareup.picasso.Picasso
import java.lang.StringBuilder

class AttractionDetailFragment : BaseFragment() {

    private var _binding: FragmentAttractionDetailBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
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
        activityViewModel.selectedAttractionLiveData.observe(viewLifecycleOwner) { attraction ->
            binding.numberOfFactsTextView.text = "${attraction.facts.size} Facts"
            binding.descriptionTextView.text = attraction.description
            binding.headerEpoxyRecyclerView.setControllerAndBuildModels(
                HeaderEpoxyController((attraction.image_urls))
            )
            //imageview next to next like one by one
            LinearSnapHelper().attachToRecyclerView(binding.headerEpoxyRecyclerView)

            binding.indicator.attachToRecyclerView(binding.headerEpoxyRecyclerView)

            binding.monthsToVisitTextView.text = attraction.months_to_visit
            binding.titleTextView.text = attraction.title
            binding.numberOfFactsTextView.setOnClickListener {
                val stringBuilder = StringBuilder("")
                attraction.facts.forEach {
                    stringBuilder.append("\u2022 $it")
                    stringBuilder.append("\n\n")
                }
                val message =
                    stringBuilder.toString()
                        .substring(0, stringBuilder.toString().lastIndexOf("\n"))
                AlertDialog.Builder(requireContext(), R.style.MyDialog)
                    .setTitle("${attraction.title} Facts")
                    .setMessage(message)
                    .setPositiveButton("Ok") { dialog, _ -> }//positive code
                    .setNegativeButton("No", { dialog, _ -> })
                    .setCancelable(false)//negative code
                    .show()
            }
        }
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu_attraction_details, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.menuItemLocation -> {
                val attraction = activityViewModel.selectedAttractionLiveData.value ?: return true
                activityViewModel.locationSelectedLiveData.postValue(attraction)
                true
            }

            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}