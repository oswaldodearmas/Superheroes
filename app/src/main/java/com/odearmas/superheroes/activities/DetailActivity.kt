package com.odearmas.superheroes.activities

import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import android.view.View
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
import java.lang.StringBuilder

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

    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {

            android.R.id.home -> {
                finish()
                true
            }

            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun loadData() {
        binding.activityDetailTitleTextView.text = hero.name
        Picasso.get().load(hero.image.imageURL).into(binding.selectedHeroImageView)
        binding.heroRealNameTextView.text = hero.biography.fullName
        binding.heroGenderTextView.text = hero.appearance.gender
        binding.heroRaceTextView.text = hero.appearance.race
        var index = hero.biography.aliases.size
        var aliasesText = ""
        hero.biography.aliases.forEach { alias ->
            aliasesText += alias
            if (--index > 0) {
                aliasesText += "\n"
            }
        }
        binding.heroAliasesTextView.text = aliasesText
        binding.heroIntelligenceTextView.text = hero.powerStats.intelligence
        binding.heroStrengthTextView.text = hero.powerStats.strength
        binding.heroSpeedTextView.text = hero.powerStats.speed
        binding.heroDurabilityTextView.text = hero.powerStats.durability
        binding.heroPowerTextView.text = hero.powerStats.power
        binding.heroCombatTextView.text = hero.powerStats.combat

    }

    private fun getHeroById(query: Int) {
        // Llamada en segundo plano
        CoroutineScope(Dispatchers.IO).launch {
            try {
                val apiService = RetrofitProvider.getRetrofit().create(HeroAPIService::class.java)
                val result = apiService.searchHeroById(query)

                runOnUiThread {
                    hero = result
                    binding.progressBar.visibility= View.GONE
                    loadData()
                }

                // Log.i("HTTP", "${result.results}")
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

}