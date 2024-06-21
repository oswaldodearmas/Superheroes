package com.odearmas.superheroes.data

import com.google.gson.annotations.SerializedName

data class HeroResponse(
    @SerializedName("response") val response: String,
    @SerializedName("results-for") val resultsFor: String
)