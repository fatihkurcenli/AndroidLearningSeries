package com.fatihkurcenli.firstapp.fragments

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.commit
import com.fatihkurcenli.firstapp.*
import com.squareup.picasso.Picasso

class DetailFragment : BaseMainActivityFragment(R.layout.fragment_detail) {
    private val soccerTile: SoccerTile by lazy {
        mainActivity.soccerTileList.find {
            it.id == requireArguments().getString("soccerTileId")
        } ?: SoccerTile()
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Log.i("Tile", soccerTile.toString())
        mainActivity.supportActionBar?.apply {
            setDisplayHomeAsUpEnabled(true)
        }

        setToolBarTitle("Kulüp Tanıtımları")

        setHasOptionsMenu(true)
        val headerImageView: ImageView = view.findViewById(R.id.teamHeaderImageView)
        val titleTextView: TextView = view.findViewById(R.id.titleTextView)
        val descriptionTextView: TextView = view.findViewById(R.id.descriptionTextView)
        val descriptionLongTextView: TextView = view.findViewById(R.id.descriptionLongTextView)

//        headerImageView.setImageResource(soccerTile.headerImageResId)
        titleTextView.text = soccerTile.title
        descriptionTextView.text = soccerTile.description
        descriptionLongTextView.text = soccerTile.descriptionLong

//        Picasso.get().isLoggingEnabled=true // this is for Log for debug
        Picasso.get().load(soccerTile.headerImageUrl).into(headerImageView)


    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)

        inflater.inflate(R.menu.menu_soccer_tile_detail, menu)
        if (soccerTile.isFavorite) {
            menu?.findItem(R.id.menuItem_favorite)?.setIcon(R.drawable.ic_favorite_24dp)

        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            android.R.id.home -> {
                mainActivity.supportFragmentManager.popBackStack()
                true

            }
            R.id.menuItem_link -> {
                Log.i("soccerTileId", soccerTile.teamUrl)
                val intent = Intent(Intent.ACTION_VIEW, Uri.parse(soccerTile.teamUrl))
                startActivity(intent)
                true
            }

            R.id.menuItem_favorite -> {
                val isCurrentlyFavorited = soccerTile.isFavorite
                if (isCurrentlyFavorited) {
                    item.setIcon(R.drawable.ic_favorite_outline_24dp)
                } else {
                    item.setIcon(R.drawable.ic_favorite_24dp)
                }

                soccerTile.isFavorite = !isCurrentlyFavorited
                SharedPrefUtil.setSoccerTileFavorite(soccerTile.id, soccerTile.isFavorite)
                true
            }

            else -> super.onOptionsItemSelected(item)
        }
    }

}