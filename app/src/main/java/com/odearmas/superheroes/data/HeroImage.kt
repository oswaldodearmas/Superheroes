package com.odearmas.superheroes.data

import com.google.gson.annotations.SerializedName
import java.net.URL

data class HeroImage(
    @SerializedName("image.url") val imageURL: URL
)
