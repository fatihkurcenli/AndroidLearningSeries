package com.fatihkurcenli.firstapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.commit
import com.fatihkurcenli.firstapp.fragments.DetailFragment
import com.fatihkurcenli.firstapp.fragments.ListFragment

class MainActivity : AppCompatActivity(), SoccerTileInterface {

    lateinit var soccerTileList: ArrayList<SoccerTile>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        soccerTileList = getList()
        supportFragmentManager.commit {
            setReorderingAllowed(true)
            add(R.id.fragmentContainerView, ListFragment())
        }
    }

    override fun onLearnMoreButtonClicked(position: Int) {
/*        val soccerTile = soccerTileList[position]
        val intent = Intent(this, SoccerTileDetailActivity::class.java).apply {
            putExtra("soccerTile", soccerTile.id)
        }
        startActivity(intent)*/
        val soccerTile = soccerTileList[position]

        supportFragmentManager.commit {
            setReorderingAllowed(true)
            addToBackStack(null)
            val bundle = Bundle().apply {
                putString("soccerTileId", soccerTile.id)
            }
            replace(R.id.fragmentContainerView, DetailFragment().apply {
                arguments = bundle
            })
        }
    }

    override fun onResume() {
        super.onResume()

    }

    override fun onFavoriteClicked(position: Int) {
        val soccerTile = soccerTileList[position]
        soccerTile.isFavorite = !soccerTile.isFavorite

        (supportFragmentManager.fragments[0] as ListFragment)?.onFavoriteClicked(position)

        SharedPrefUtil.setSoccerTileFavorite(soccerTile.id, soccerTile.isFavorite)


        /**
         * [soccerTileAdapter.notifyDataSetChanged()]tum datalari degistirmekte ancak bizim istedigimiz
         * elimizde position bulundungundan [soccerTileAdapter.notifyItemChanged(position)] ile sadece
         * degismek istedigimiz konumun adresini degistirebilmekteyiz.
         */
    }

    private fun getList(): ArrayList<SoccerTile> {
        return ArrayList<SoccerTile>().apply {
            add(
                SoccerTile(
                    id = "besiktas",
                    title = "Beşiktaş",
                    description = "Beşiktaş En büyük Takım",
                    descriptionLong = "Lorem Ipsum is simply dummy text of the printing and typesetting industry. " +
                            "Lorem Ipsum has been the industry's standard dummy text ever since the 1500s." +
                            "Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised in the 1960s with the release of " +
                            "Letraset sheets containing Lorem Ipsum passages, and more" +
                            " recently with desktop publishing software like Aldus PageMaker" +
                            " including versions of Lorem Ipsum.",
                    buttonText = "Learn More",
                    headerImageResId = R.drawable.besiktas,
                    headerImageUrl = "https://i.pinimg.com/originals/c7/99/92/c79992662084735c2385fb3613d1456b.jpg",
                    teamUrl = "https://bjk.com.tr/tr",
                    isFavorite = SharedPrefUtil.getSoccerTileFavorite("besiktas")
                )
            )
            add(
                SoccerTile(
                    id = "fenerbahce",
                    title = "Fenerbahçe",
                    description = "Fenerbahçe takımı",
                    descriptionLong = "Lorem Ipsum is simply dummy text of the printing and typesetting industry. " +
                            "Lorem Ipsum has been the industry's standard dummy text ever since the 1500s." +
                            "Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised in the 1960s with the release of " +
                            "Letraset sheets containing Lorem Ipsum passages, and more" +
                            " recently with desktop publishing software like Aldus PageMaker" +
                            " including versions of Lorem Ipsum.",
                    buttonText = "Learn More",
                    headerImageResId = R.drawable.fenerbahce,
                    headerImageUrl = "https://pbs.twimg.com/media/EVjdHCrWsAA9cly.jpg",
                    teamUrl = "https://www.fenerbahce.org/",
                    isFavorite = SharedPrefUtil.getSoccerTileFavorite("fenerbahce")
                )
            )
            add(
                SoccerTile(
                    id = "galatasaray",
                    title = "Galatasaray",
                    description = "Galatasaray takımı",
                    descriptionLong = "Lorem Ipsum is simply dummy text of the printing and typesetting industry. " +
                            "Lorem Ipsum has been the industry's standard dummy text ever since the 1500s." +
                            "Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised in the 1960s with the release of " +
                            "Letraset sheets containing Lorem Ipsum passages, and more" +
                            " recently with desktop publishing software like Aldus PageMaker" +
                            " including versions of Lorem Ipsum.",
                    buttonText = "Learn More",
                    headerImageResId = R.drawable.galatasaray,
                    headerImageUrl = "https://data.whicdn.com/images/294694932/original.png",
                    teamUrl = "https://www.galatasaray.org/anasayfa",
                    isFavorite = SharedPrefUtil.getSoccerTileFavorite("galatasaray")
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