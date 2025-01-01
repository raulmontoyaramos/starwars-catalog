package com.example.practicar_android.domain.model

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.practicar_android.data.room.dao.CharacterDao
import com.example.practicar_android.data.room.dao.FilmDao
import com.example.practicar_android.data.room.dao.WorldDao
import com.example.practicar_android.data.room.entity.CharacterEntity
import com.example.practicar_android.data.room.entity.FilmEntity
import com.example.practicar_android.data.room.entity.WorldEntity
import com.example.practicar_android.domain.model.util.InstantConverter
import com.example.practicar_android.domain.model.util.ListConverter

@Database(
    entities = [CharacterEntity::class, FilmEntity::class, WorldEntity::class],
    version = 4,
    exportSchema = false
)
@TypeConverters(ListConverter::class, InstantConverter::class)
abstract class StarWarsDatabase : RoomDatabase() {

    abstract fun characterDao(): CharacterDao
    abstract fun filmDao(): FilmDao
    abstract fun worldDao(): WorldDao

    companion object {
        @Volatile
        private var Instance: StarWarsDatabase? = null
        fun getDatabase(context: Context): StarWarsDatabase {

            return Instance ?: synchronized(this) {
                Room.databaseBuilder(context, StarWarsDatabase::class.java, "starwars_database")
                    .fallbackToDestructiveMigration()
                    .build()
                    .also { Instance = it }
            }
        }
    }
}
