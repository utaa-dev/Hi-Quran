package com.example.hi_quran.generator.domain.model

data class GeneratorMetadata(
    val version: String,
    val generatedAt: String,
    val apiSource: String,
    val sourceLicense: String,
    val locale: String,
    val generatedBy: String
)
