package com.example.practicar_android.domain.model.util

import androidx.room.TypeConverter

class ListConverter {
    @TypeConverter
    fun fromListToString(list: List<String>): String = list.toString()

    @TypeConverter
    fun fromStringToList(string: String): List<String> {
        val result = ArrayList<String>()
        val split = string.replace("[", "").replace("]", "").replace(" ", "").split(",")
        for (i in split) {
            try {
                result.add(i)
            } catch (e: Exception) {

            }
        }
        return result
    }
}
