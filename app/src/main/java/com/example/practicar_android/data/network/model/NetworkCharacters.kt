package com.example.practicar_android.data.network.model


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class NetworkCharacters(
    @SerialName("count")
    val count: Int = 0,
    @SerialName("next")
    val next: String = "",
    @SerialName("previous")
    val previous: String? = null,
    @SerialName("results")
    val results: List<NetworkCharacter> = listOf()
)

@Serializable
data class NetworkCharacter(
    @SerialName("name")
    val name: String,
    @SerialName("height")
    val height: String,
    @SerialName("mass")
    val mass: String,
    @SerialName("hair_color")
    val hairColor: String,
    @SerialName("skin_color")
    val skinColor: String,
    @SerialName("eye_color")
    val eyeColor: String,
    @SerialName("birth_year")
    val birthYear: String,
    @SerialName("gender")
    val gender: String,
    @SerialName("homeworld")
    val homeworld: String,
    @SerialName("films")
    val films: List<String>,
    @SerialName("species")
    val species: List<String>,
    @SerialName("vehicles")
    val vehicles: List<String>,
    @SerialName("starships")
    val starships: List<String>,
    @SerialName("created")
    val created: String?,
    @SerialName("edited")
    val edited: String?,
    @SerialName("url")
    val url: String
)
