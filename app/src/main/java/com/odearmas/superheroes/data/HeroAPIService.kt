package com.odearmas.superheroes.data

import retrofit2.http.GET
import retrofit2.http.Path

interface HeroAPIService {
    @GET("search/{name}")
    suspend fun searchHeroesByName(@Path("name") query:String): HeroListResponse

    @GET("{id}")
    suspend fun searchHeroById(@Path("id") query:Int): HeroResponse

}
