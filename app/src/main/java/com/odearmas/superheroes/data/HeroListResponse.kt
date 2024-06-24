package com.odearmas.superheroes.data

import com.google.gson.annotations.SerializedName

data class HeroListResponse(
    @SerializedName("response") val response: String,
    @SerializedName("results-for") val resultsFor: String,
    @SerializedName("results") val results: List<HeroResponse>
)