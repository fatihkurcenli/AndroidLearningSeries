package com.fatihkurcenli.myholiday.ui.fragment

import androidx.fragment.app.Fragment
import com.fatihkurcenli.myholiday.MainActivity
import com.fatihkurcenli.myholiday.data.Attraction

abstract class BaseFragment : Fragment() {
    protected val navController by lazy {
        (activity as MainActivity).navController
    }

    protected val attractions: List<Attraction>
        get() = (activity as MainActivity).attractionList
}