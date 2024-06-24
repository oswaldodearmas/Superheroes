package com.odearmas.superheroes.activities

import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.odearmas.superheroes.R
import com.odearmas.superheroes.data.HeroAPIService
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        searchByName("super")
    }

    private fun searchByName(query: String) {
        // Llamada en segundo plano
        CoroutineScope(Dispatchers.IO).launch {
            try {
                val apiService = getRetrofit().create(HeroAPIService::class.java)
                val result = apiService.findHeroByName(query)
                Log.i("HTTP", "${result.results}")
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }
    private fun getRetrofit(): Retrofit {
        // El interceptor permite capturar el error y msotrarlo en el Log
        /*val interceptor = HttpLoggingInterceptor()
        interceptor.setLevel(HttpLoggingInterceptor.Level.BASIC)
        val client: OkHttpClient = OkHttpClient.Builder().addInterceptor(interceptor).build()*/

        return Retrofit.Builder()
            .baseUrl("https://superheroapi.com/api/7252591128153666/")
            //.client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

}