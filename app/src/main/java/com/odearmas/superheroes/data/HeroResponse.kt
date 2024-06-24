package com.odearmas.superheroes.data

import com.google.gson.annotations.SerializedName

data class HeroResponse(
    @SerializedName("name") val name: String
)