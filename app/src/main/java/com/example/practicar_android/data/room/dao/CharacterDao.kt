package com.example.practicar_android.data.room.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.TypeConverters
import com.example.practicar_android.data.room.entity.CharacterEntity
import com.example.practicar_android.domain.model.util.InstantConverter
import com.example.practicar_android.domain.model.util.ListConverter
import kotlinx.coroutines.flow.Flow

@Dao
@TypeConverters(ListConverter::class, InstantConverter::class)
interface CharacterDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertCharacter(character: CharacterEntity)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertCharacters(characters: List<CharacterEntity>)

    @Query("SELECT * FROM characters WHERE characterId = :characterId")
    fun getCharacter(characterId: Int): CharacterEntity?

    @Query("SELECT * FROM characters ORDER BY name ASC")
    fun getAllCharacters(): Flow<List<CharacterEntity>>
}
