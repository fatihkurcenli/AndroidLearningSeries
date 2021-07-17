package com.fatihkurcenli.firstapp.fragments

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.fatihkurcenli.firstapp.*


class ListFragment : BaseMainActivityFragment(R.layout.fragment_list) {

    private lateinit var recyclerView: RecyclerView
    private lateinit var soccerTileAdapter: SoccerTileAdapter
    private val soccerTileList: ArrayList<SoccerTile>
        get() = mainActivity.soccerTileList

    override fun onCreate(savedInstanceState: Bundle?) {
        Log.i("listFragmnet", "oncreate")
        super.onCreate(savedInstanceState)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        mainActivity.supportActionBar?.apply {

            setDisplayHomeAsUpEnabled(false)
        }
        setToolBarTitle("Türkiye Takımları")

        soccerTileAdapter = SoccerTileAdapter(soccerTileList, mainActivity)
        recyclerView = view.findViewById(R.id.recyclerView)
        recyclerView.adapter = soccerTileAdapter
    }

    override fun onResume() {
        super.onResume()
        soccerTileAdapter.notifyDataSetChanged()
    }

    fun onFavoriteClicked(position: Int) {
        soccerTileAdapter.notifyItemChanged(position)
    }


    /**
     * asagidaki kod blogu sayesidende fragmentlari tanimlama edebilmekteyiz.
     * istersek Fragment(R.layout.fragment_list) diyerekte tanimlama edebiliriz.
     */

    /*    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_list, container, false)
    }*/
}