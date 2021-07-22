package com.fatihkurcenli.myholiday.ui.fragment

import androidx.fragment.app.Fragment
import com.fatihkurcenli.myholiday.MainActivity

abstract class BaseFragment : Fragment() {
    protected val navController by lazy {
        (activity as MainActivity).navController
    }
}