package com.example.practicar_android.domain.model.repositories

import com.example.practicar_android.domain.model.World
import kotlinx.coroutines.flow.Flow

interface WorldsRepository {
    suspend fun insertWorld(world: World)

    suspend fun insertWorlds(worlds: List<World>)

    fun getWorld(worldId: String): World?

    fun getAllWorlds(): Flow<List<World>>
}