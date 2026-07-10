package com.example.hi_quran.generator.api

import com.example.hi_quran.generator.GeneratorConfig
import com.example.hi_quran.generator.model.SurahDetailDto
import com.example.hi_quran.generator.model.SurahItemDto
import kotlinx.coroutines.delay
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.kotlinx.serialization.asConverterFactory
import java.util.concurrent.TimeUnit

class QuranFetcher(private val config: GeneratorConfig) {

    private val api: QuranApiService

    init {
        val logging = HttpLoggingInterceptor { message -> 
            if (message.startsWith("--> GET") || message.startsWith("<-- 200") || message.contains("FAILED")) {
                println("[NETWORK] $message") 
            }
        }
        logging.level = HttpLoggingInterceptor.Level.BASIC

        val client = OkHttpClient.Builder()
            .addInterceptor(logging)
            .connectTimeout(config.apiTimeout, TimeUnit.SECONDS)
            .readTimeout(config.apiTimeout, TimeUnit.SECONDS)
            .build()

        val json = Json { ignoreUnknownKeys = true }
        val retrofit = Retrofit.Builder()
            .baseUrl(config.baseUrl)
            .client(client)
            .addConverterFactory(json.asConverterFactory("application/json".toMediaType()))
            .build()

        api = retrofit.create(QuranApiService::class.java)
    }

    suspend fun fetchSurahList(): List<SurahItemDto> {
        return executeWithRetry("Surah List") {
            val response = api.getSurahList()
            if (response.code == 200) {
                validateSurahList(response.data)
                response.data
            } else {
                throw Exception("API Error: ${response.message}")
            }
        }
    }

    suspend fun fetchSurahDetail(nomor: Int): SurahDetailDto {
        return executeWithRetry("Surah Detail #$nomor") {
            val response = api.getSurahDetail(nomor)
            if (response.code == 200) {
                validateSurahDetail(response.data)
                response.data
            } else {
                throw Exception("API Error: ${response.message}")
            }
        }
    }

    private suspend fun <T> executeWithRetry(
        taskName: String,
        block: suspend () -> T
    ): T {
        var lastException: Exception? = null
        for (attempt in 1..config.maxRetry) {
            try {
                return block()
            } catch (e: Exception) {
                lastException = e
                println("[RETRY] Failed to fetch $taskName (Attempt $attempt/${config.maxRetry}): ${e.message}")
                if (attempt < config.maxRetry) delay(2000L * attempt) // Exponential backoff
            }
        }
        throw Exception("Failed to fetch $taskName after ${config.maxRetry} attempts. Last error: ${lastException?.message}")
    }

    private fun validateSurahList(data: List<SurahItemDto>) {
        if (data.isEmpty()) throw Exception("API returned empty surah list")
        if (data.any { it.nomor <= 0 || it.namaLatin.isBlank() }) {
            throw Exception("API returned invalid surah metadata")
        }
    }

    private fun validateSurahDetail(data: SurahDetailDto) {
        if (data.ayat.isEmpty()) throw Exception("Surah ${data.nomor} has no ayahs")
        if (data.ayat.any { it.teksArab.isBlank() || it.teksIndonesia.isBlank() }) {
            throw Exception("Found empty ayah content in Surah ${data.nomor}")
        }
    }
}
