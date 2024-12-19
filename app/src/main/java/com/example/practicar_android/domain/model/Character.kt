package com.example.practicar_android.domain.model

import com.example.practicar_android.data.room.entity.CharacterEntity
import com.example.practicar_android.domain.model.Character
import java.time.Instant

data class Character(
    val id: String,
    val name: String,
    val height: String,
    val mass: String,
    val hairColor: String,
    val skinColor: String,
    val eyeColor: String,
    val birthYear: String,
    val gender: String,
    val homeworld: String,
    val films: List<String>,
    val species: List<String>,
    val vehicles: List<String>,
    val starships: List<String>,
    val created: Instant,
    val edited: Instant,
    val url: String
)

fun Character.toDatabase() =
    CharacterEntity(
        characterId = id,
        name = name,
        gender = gender,
        skinColor = skinColor,
        hairColor = hairColor,
        eyeColor = eyeColor,
        height = height,
        mass = mass,
        birthYear = birthYear,
        homeworld = homeworld,
        films = films,
        species = species,
        vehicles = vehicles,
        starships = starships,
        created = created,
        edited = edited,
        url = url
    )

fun CharacterEntity.toDomain() =
    Character(
        id = characterId,
        name = name,
        gender = gender,
        skinColor = skinColor,
        hairColor = hairColor,
        eyeColor = eyeColor,
        height = height,
        mass = mass,
        birthYear = birthYear,
        homeworld = homeworld,
        films = films,
        species = species,
        vehicles = vehicles,
        starships = starships,
        created = created,
        edited = edited,
        url = url,
    )
