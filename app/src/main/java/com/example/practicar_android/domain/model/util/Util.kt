package com.example.practicar_android.domain.model.util

fun String.extractIdFromUrl(): String {
    return split("/").dropLast(1).last()
}
