package com.example.practicar_android.data.room.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.time.Instant

@Entity(tableName = "worlds")
data class WorldEntity(
    @PrimaryKey
    val worldId: String,
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
