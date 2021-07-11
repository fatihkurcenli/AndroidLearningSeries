package com.fatihkurcenli.firstapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity(), SoccerTileInterface {
    private lateinit var soccerTileList: ArrayList<SoccerTile>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        soccerTileList = getSoccerTileList()
        val soccerTileAdapter = SoccerTileAdapter(soccerTileList, this)
        val recyclerView = findViewById<RecyclerView>(R.id.recyclerView)
        recyclerView.adapter = soccerTileAdapter

        soccerTileAdapter.notifyDataSetChanged()



    }

    override fun onLearnMoreButtonClicked(position: Int) {
        startActivity(Intent(this, SoccerTileDetailActivity::class.java))
    }

    private fun getSoccerTileList(): ArrayList<SoccerTile> {
        return ArrayList<SoccerTile>().apply {
            add(
                SoccerTile(
                    id = "besiktas",
                    title = "Beşiktaş",
                    description = "Beşiktaş En büyük Takım",
                    descriptionLong = "Lorem Ipsum is simply dummy text of the printing and typesetting industry. " +
                            "Lorem Ipsum has been the industry's standard dummy text ever since the 1500s.",
                    buttonText = "Learn More",
                    headerImageResId = R.drawable.besiktas,
                    headerImageUrl = "https://i.pinimg.com/originals/c7/99/92/c79992662084735c2385fb3613d1456b.jpg",
                )
            )
            add(
                SoccerTile(
                    id = "fenerbahce",
                    title = "Fenerbahçe",
                    description = "Fenerbahçe takımı",
                    descriptionLong = "Lorem Ipsum is simply dummy text of the printing and typesetting industry. " +
                            "Lorem Ipsum has been the industry's standard dummy text ever since the 1500s.",
                    buttonText = "Learn More",
                    headerImageResId = R.drawable.fenerbahce,
                    headerImageUrl = "https://pbs.twimg.com/media/EVjdHCrWsAA9cly.jpg",
                )
            )
            add(
                SoccerTile(
                    id = "galatasaray",
                    title = "Galatasaray",
                    description = "Galatasaray takımı",
                    descriptionLong = "Lorem Ipsum is simply dummy text of the printing and typesetting industry. " +
                            "Lorem Ipsum has been the industry's standard dummy text ever since the 1500s.",
                    buttonText = "Learn More",
                    headerImageResId = R.drawable.galatasaray,
                    headerImageUrl = "https://data.whicdn.com/images/294694932/original.png",
                )
            )
        }
    }


}


/**
 * besiktas image url = "https://i.pinimg.com/originals/c7/99/92/c79992662084735c2385fb3613d1456b.jpg"
 * fenerbahce image url= "https://pbs.twimg.com/media/EVjdHCrWsAA9cly.jpg"
 * galatasaray image url = "https://data.whicdn.com/images/294694932/original.png"
 * */