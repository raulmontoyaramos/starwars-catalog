package com.example.practicar_android.data.network.model

import com.example.practicar_android.domain.model.World
import kotlinx.coroutines.flow.MutableStateFlow

class WorldRepository {

    val worlds = MutableStateFlow<Map<String, World>>(emptyMap())

    suspend fun setWorld(world: World) {
        this.worlds.value = worlds.value.plus(world.id to world)
    }

    suspend fun getWorldById(worldId: String): World? = worlds.value[worldId]
}
