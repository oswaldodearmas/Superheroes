package com.odearmas.superheroes.utils

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitProvider {
    companion object{

        fun getRetrofit(): Retrofit {
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
}