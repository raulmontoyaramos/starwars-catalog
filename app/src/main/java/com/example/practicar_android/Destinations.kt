package com.example.practicar_android

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import kotlinx.serialization.Serializable

@Serializable
object CharacterList

@Serializable
@Parcelize
data class CharacterDetails(
    val characterId: String
) : Parcelable

@Serializable
@Parcelize
data class WorldDetails(
    val worldId: String
) : Parcelable

@Serializable
@Parcelize
data class FilmDetails(
    val filmId: String
) : Parcelable
