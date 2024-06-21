package com.odearmas.superheroes.data

import retrofit2.http.GET
import retrofit2.http.Path

interface HeroAPIService {
    @GET("search/{name}")
    suspend fun findHeroByName(@Path("name") query:String): HeroListResponse
}