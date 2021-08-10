package com.fatihkurcenli.myholiday.ui.fragment

import androidx.fragment.app.Fragment
import com.fatihkurcenli.myholiday.MainActivity
import com.fatihkurcenli.myholiday.arch.AttractionsViewModel
import com.fatihkurcenli.myholiday.data.Attraction

abstract class BaseFragment : Fragment() {
    protected val navController by lazy {
        (activity as MainActivity).navController
    }

    protected val activityViewModel: AttractionsViewModel
        get() = (activity as MainActivity).viewModel
}