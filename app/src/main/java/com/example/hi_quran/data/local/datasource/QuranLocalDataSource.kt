package com.example.hi_quran.data.local.datasource

import android.content.Context
import kotlinx.serialization.json.Json
import java.io.InputStreamReader

class QuranLocalDataSource(private val context: Context) {
    private val json = Json { ignoreUnknownKeys = true }

    fun getQuranFromAssets(): Result<QuranResponseDto> {
        return runCatching {
            val content = context.assets.open("quran.json").bufferedReader().use { it.readText() }
            json.decodeFromString<QuranResponseDto>(content)
        }
    }

    fun getJuzFromAssets(): Result<JuzResponseDto> {
        return runCatching {
            val content = context.assets.open("juz.json").bufferedReader().use { it.readText() }
            json.decodeFromString<JuzResponseDto>(content)
        }
    }

    fun getDoasFromAssets(): Result<DoaResponseDto> {
        return runCatching {
            val content = context.assets.open("doa.json").bufferedReader().use { it.readText() }
            json.decodeFromString<DoaResponseDto>(content)
        }
    }
}
