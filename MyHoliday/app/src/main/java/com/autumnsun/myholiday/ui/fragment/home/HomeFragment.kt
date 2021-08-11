package com.fatihkurcenli.myholiday.ui.fragment.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.RecyclerView
import com.autumnsun.myholiday.R
import com.autumnsun.myholiday.databinding.FragmentHomeBinding
import com.fatihkurcenli.myholiday.ui.fragment.BaseFragment

class HomeFragment : BaseFragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        super.onViewCreated(view, savedInstanceState)
        val epoxyController = HomeFragmentEpoxyController { attractionId ->
            activityViewModel.onAttractionSelected(attractionId)
            navController.navigate(R.id.action_homeFragment_to_attractionDetailFragment)
        }

        binding.epoxyRecyclerView.setController(epoxyController)
        binding.epoxyRecyclerView.addItemDecoration(
            DividerItemDecoration(requireActivity(), RecyclerView.VERTICAL)
        )

        epoxyController.isLoading = true
        //Observing changes to the underlying list of data
        activityViewModel.attractionsListLiveData.observe(viewLifecycleOwner) { attractions ->
            epoxyController.attractions = attractions
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

}