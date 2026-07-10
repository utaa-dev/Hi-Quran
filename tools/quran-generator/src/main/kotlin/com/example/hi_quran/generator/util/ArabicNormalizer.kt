package com.example.hi_quran.generator.util

object ArabicNormalizer {
    /**
     * Menghapus harakat/diakritik dari teks Arab untuk memudahkan pencarian.
     */
    fun normalize(text: String): String {
        // Regex untuk karakter harakat (Fathah, Kasrah, Dammah, Tanwin, Sukun, Shaddah, dll)
        val harakatRegex = Regex("[\u064B-\u065F\u0670\u06D6-\u06ED]")
        return text.replace(harakatRegex, "")
    }
}
