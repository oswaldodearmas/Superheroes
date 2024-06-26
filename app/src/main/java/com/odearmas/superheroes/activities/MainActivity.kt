package com.odearmas.superheroes.activities

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.appcompat.widget.SearchView.*
import androidx.recyclerview.widget.GridLayoutManager
import com.odearmas.superheroes.R
import com.odearmas.superheroes.adapters.HeroAdapter
import com.odearmas.superheroes.data.HeroAPIService
import com.odearmas.superheroes.data.HeroListResponse
import com.odearmas.superheroes.data.HeroResponse
import com.odearmas.superheroes.databinding.ActivityMainBinding
import com.odearmas.superheroes.utils.RetrofitProvider
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding
    lateinit var heroListResponse: HeroListResponse
    lateinit var adapter: HeroAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        adapter = HeroAdapter { position -> navigateToDetail(heroListResponse.results[position])}

        binding.recyclerView.adapter = adapter
        binding.recyclerView.layoutManager = GridLayoutManager(this, 2)

        searchByName("bat")

    }

    private fun searchByName(query: String) {
        // Llamada en segundo plano
        CoroutineScope(Dispatchers.IO).launch {
            try {
                val apiService = RetrofitProvider.getRetrofit().create(HeroAPIService::class.java)
                val result = apiService.searchHeroesByName(query)
                if (result.response == "success") {
                    runOnUiThread {
                        heroListResponse = result
                        adapter.updateData(result.results)
                    }
                } else {
                    runOnUiThread {
                        adapter.updateData(emptyList())
                    }
                }
                // Log.i("HTTP", "${result.results}")
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.activity_main_menu, menu)
        val searchViewItem = menu.findItem(R.id.menu_search)
        val searchView = searchViewItem.actionView as SearchView

        searchView.setOnQueryTextListener(object : OnQueryTextListener {

            override fun onQueryTextSubmit(newText: String?): Boolean {

                searchViewItem.collapseActionView()
                if (newText != null) {
                    searchByName(newText)
                }
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                if (newText != null) {
                    searchByName(newText)
                }
                return true
            }

        })
        return true
    }

    private fun navigateToDetail(hero: HeroResponse) {
        val callDetail: Intent = Intent(this, DetailActivity::class.java)
        callDetail.putExtra("HERO_ID", hero.id)
        startActivity(callDetail)
    }
}