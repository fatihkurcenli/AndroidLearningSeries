package com.fatihkurcenli.firstapp

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class SoccerTileDetailActivity : AppCompatActivity() {

    private lateinit var soccerTile: SoccerTile


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_socceer_tile_detail)

        supportActionBar?.apply {
            setDisplayHomeAsUpEnabled(true)
            title = "Club Overview"
        }


        val selectedSoccerTile = intent.getStringExtra("soccerTile")
        soccerTile = MainActivity.soccerTileList.find {
            it.id == selectedSoccerTile
        } ?: SoccerTile(
            title = "Whoops",
            description = "Something wrong please try again"
        )

        val headerImageView: ImageView = findViewById(R.id.teamHeaderImageView)
        val titleTextView: TextView = findViewById(R.id.titleTextView)
        val descriptionTextView: TextView = findViewById(R.id.descriptionTextView)
        val descriptionLongTextView: TextView = findViewById(R.id.descriptionLongTextView)

        headerImageView.setImageResource(soccerTile.headerImageResId)
        titleTextView.text = soccerTile.title
        descriptionTextView.text = soccerTile.description
        descriptionLongTextView.text = soccerTile.descriptionLong


/*        if (::soccerTile.isInitialized){
            //Eger initialized edilip edilmedigini kontrol edebiliyoruz. Buna göre islemlere devam
            //edebiliriz.
        }*/

//        soccerTile = intent.getSerializableExtra("soccerTile") as? SoccerTile
    }


    /**
     * üst taraftan kullancilarin geri gelmesini saglayan yapidir. Burada itemId bakarak
     * home activity ekleyerek boolean bir değer dönmekte ve activity tekrardan cagirmakta
     * onCreateOptionsMenu sayesinde ise diger menulere ovverride edebilmekteyiz.
     */
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            android.R.id.home -> {
                finish()
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
                true
            }

            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_soccer_tile_detail, menu)
        if (soccerTile.isFavorite) {
            menu?.findItem(R.id.menuItem_favorite)?.setIcon(R.drawable.ic_favorite_24dp)
        }
        return true
    }

    /* override fun onCreateOptionsMenu(menu: Menu?): Boolean {
         return super.onCreateOptionsMenu(menu)
     }*/
}