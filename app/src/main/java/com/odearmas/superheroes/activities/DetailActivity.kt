package com.odearmas.superheroes.activities

import android.os.Bundle
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.odearmas.superheroes.R
import com.odearmas.superheroes.data.HeroAPIService
import com.odearmas.superheroes.data.HeroResponse
import com.odearmas.superheroes.databinding.ActivityDetailBinding
import com.odearmas.superheroes.databinding.ActivityMainBinding
import com.odearmas.superheroes.databinding.ItemHeroBinding
import com.odearmas.superheroes.utils.RetrofitProvider
import com.squareup.picasso.Picasso
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class DetailActivity : AppCompatActivity() {

    private lateinit var hero: HeroResponse
    private lateinit var binding: ActivityDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //Recibir la petición desde activity_main y construir la vista de detalle
        val heroId = intent.getIntExtra("HERO_ID", -1)

        getHeroById(heroId)

        supportActionBar?.setDisplayHomeAsUpEnabled(true) //Flecha de ir atrás
//        if (heroName != null) {
//            val hero = searchByName(heroName)
//            //val zodiac = zodiacId?.let { HoroscopeItem.fromId(it) }
//            if (zodiac != null) {
//                supportActionBar?.setDisplayHomeAsUpEnabled(true) //Flecha de ir atrásf
//                findViewById<TextView>(R.id.selected_name_textView).text =
//                    getString(zodiac.zodiacName)
//                findViewById<TextView>(R.id.selected_date_textView).text = getString(zodiac.date)
//                findViewById<ImageView>(R.id.selected_icon_imageView).setImageResource(zodiac.logo)
//                getDailyHoroscope()
//
//                //determinar cuál ítem de horóscopo se ha seleccionado mediante contador
//                var i: Int = 0
//                for (item in horoscopeList) {
//                    (if (horoscopeList[i].id != zodiac.id) {
//                        i++
//                    } else {
//                        break
//                    })
//                }
//                position = i
//            }
//        }
//    }
//

    }

    private fun loadData() {
        binding.activityDetailTitleTextView.text = hero.name
        Picasso.get().load(hero.image.imageURL).into(binding.selectedHeroImageView)
        TODO() //Bind and display hero details
    }

    private fun getHeroById(query: Int) {
        // Llamada en segundo plano
        CoroutineScope(Dispatchers.IO).launch {
            try {
                val apiService = RetrofitProvider.getRetrofit().create(HeroAPIService::class.java)
                val result = apiService.searchHeroById(query)

                runOnUiThread {
                    hero = result
                    loadData()
                }

                // Log.i("HTTP", "${result.results}")
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

}