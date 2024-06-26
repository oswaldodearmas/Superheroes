package com.odearmas.superheroes.data

import android.media.Image
import com.google.gson.annotations.SerializedName

data class HeroResponse(
    @SerializedName("name") val name: String,
    @SerializedName("image") val image: HeroImage

)
