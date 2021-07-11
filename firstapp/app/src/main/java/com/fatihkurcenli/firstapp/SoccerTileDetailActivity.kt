package com.fatihkurcenli.firstapp

import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity

class SoccerTileDetailActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_socceer_tile_detail)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
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
            else -> super.onOptionsItemSelected(item)
        }
    }

    /* override fun onCreateOptionsMenu(menu: Menu?): Boolean {
         return super.onCreateOptionsMenu(menu)
     }*/
}