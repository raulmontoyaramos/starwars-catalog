package com.example.practicar_android.data.room.repository

import com.example.practicar_android.data.room.dao.WorldDao
import com.example.practicar_android.domain.model.World
import com.example.practicar_android.domain.model.repositories.WorldsRepository
import com.example.practicar_android.domain.model.toDatabase
import com.example.practicar_android.domain.model.toDomain
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class OfflineWorldsRepository(private val worldDao: WorldDao) : WorldsRepository {

    override suspend fun insertWorld(world: World) =
        worldDao.insertWorld(world.toDatabase())

    override suspend fun insertWorlds(worlds: List<World>) =
        worldDao.insertWorlds(worlds.map { it.toDatabase() })

    override fun getWorld(worldId: String): World? =
        worldDao.getWorld(worldId)?.toDomain()

    override fun getAllWorlds(): Flow<List<World>> =
        worldDao.getAllWorlds().map { it.map { it.toDomain() } }
}