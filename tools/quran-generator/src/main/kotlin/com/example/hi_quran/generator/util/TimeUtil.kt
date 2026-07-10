package com.example.hi_quran.generator.util

import java.time.Duration

object TimeUtil {
    fun formatDuration(millis: Long): String {
        val duration = Duration.ofMillis(millis)
        val hours = duration.toHours()
        val minutes = duration.toMinutesPart()
        val seconds = duration.toSecondsPart()
        return if (hours > 0) {
            String.format("%02d:%02d:%02d", hours, minutes, seconds)
        } else {
            String.format("%02d:%02d", minutes, seconds)
        }
    }

    fun calculateEta(startTime: Long, currentItem: Int, totalItems: Int): Long {
        if (currentItem <= 0) return 0
        val elapsed = System.currentTimeMillis() - startTime
        val timePerItem = elapsed.toDouble() / currentItem
        val remainingItems = totalItems - currentItem
        return (timePerItem * remainingItems).toLong()
    }
}
