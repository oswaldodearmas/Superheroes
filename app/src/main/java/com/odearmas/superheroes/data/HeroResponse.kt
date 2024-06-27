package com.odearmas.superheroes.data

import com.google.gson.annotations.SerializedName

data class HeroResponse(
    @SerializedName("name") val name: String,
    @SerializedName("image") val image: HeroImage,
    @SerializedName("id") val id: Int,
    @SerializedName("response") val response: String,
    @SerializedName("powerstats") val powerStats: HeroStats,
    @SerializedName("biography") val biography: Biography,
    @SerializedName("appearance") val appearance: Appearace
)
data class Appearace (
    @SerializedName("gender") val gender: String,
    @SerializedName("race") val race: String
)
data class Biography (
    @SerializedName("full-name") val fullName: String,
    @SerializedName("alignment") val alignment: String,
    @SerializedName("publisher") val publisher: String,
    @SerializedName("aliases") val aliases: List<String>
)

data class HeroStats (
    @SerializedName("intelligence") val intelligence: String,
    @SerializedName("strength") val strength: String,
    @SerializedName("speed") val speed: String,
    @SerializedName("durability") val durability: String,
    @SerializedName("power") val power: String,
    @SerializedName("combat") val combat: String
)

data class HeroImage(
    @SerializedName("url") val imageURL: String
)

