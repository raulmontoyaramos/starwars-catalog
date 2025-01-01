package com.example.practicar_android.data.room.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.practicar_android.data.room.entity.WorldEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface WorldDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertWorld(world: WorldEntity)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertWorlds(worlds: List<WorldEntity>)

    @Query("SELECT * FROM worlds WHERE worldId = :worldId")
    fun getWorld(worldId: String): WorldEntity?

    @Query("SELECT * FROM worlds ORDER BY name ASC")
    fun getAllWorlds(): Flow<List<WorldEntity>>
}