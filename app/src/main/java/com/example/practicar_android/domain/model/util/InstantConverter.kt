package com.example.practicar_android.domain.model.util

import androidx.room.TypeConverter
import java.time.Instant

class InstantConverter {

    @TypeConverter
    fun fromInstant(instant: Instant?): String? {
        return instant?.toString()
    }

    @TypeConverter
    fun toInstant(string: String?): Instant? {
        return string?.let { Instant.parse(it) }
    }
}