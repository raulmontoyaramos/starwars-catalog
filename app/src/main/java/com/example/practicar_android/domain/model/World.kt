package com.example.practicar_android.domain.model

import com.example.practicar_android.data.room.entity.WorldEntity
import java.time.Instant

data class World(
    val id: String,
    val name: String,
    val rotationPeriod: String,
    val orbitalPeriod: String,
    val diameter: String,
    val climate: String,
    val gravity: String,
    val terrain: String,
    val surfaceWater: String,
    val population: String,
    val residents: List<String>,
    val films: List<String>,
    val created: Instant,
    val edited: Instant,
    val url: String
)

fun World.toDatabase() =
    WorldEntity(
        worldId = id,
        name = name,
        rotationPeriod = rotationPeriod,
        orbitalPeriod = orbitalPeriod,
        diameter = diameter,
        climate = climate,
        gravity = gravity,
        terrain = terrain,
        surfaceWater = surfaceWater,
        population = population,
        residents = residents,
        films = films,
        created = created,
        edited = edited,
        url = url
)

fun WorldEntity.toDomain() =
    World(
        id = worldId,
        name = name,
        rotationPeriod = rotationPeriod,
        orbitalPeriod = orbitalPeriod,
        diameter = diameter,
        climate = climate,
        gravity = gravity,
        terrain = terrain,
        surfaceWater = surfaceWater,
        population = population,
        residents = residents,
        films = films,
        created = created,
        edited = edited,
        url = url
    )

