package com.astecnology.newspluse.util

import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale
import java.util.TimeZone

/**
 * Converts a given time string in the format "yyyy-MM-dd'T'HH:mm:ss'Z'"
 * to a human-readable string representing the time elapsed since the provided time.
 *
 * @param timeString The time string to convert.
 * @return A human-readable string representing the time elapsed since the provided time.
 * If the input is invalid or empty, an empty string is returned.
 */
object NewsUtils {
    fun getTimeAgoString(timeString: String): String {
        // Set up date format and time zone
        val sdf = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'", Locale.getDefault())
        sdf.timeZone = TimeZone.getTimeZone("UTC")
        // Get current time
        val currentTime = Calendar.getInstance().timeInMillis
        // Parse the provided time string
        val time = sdf.parse(timeString)?.time ?: return ""
        // Calculate time difference in milliseconds
        val difference = currentTime - time
        // Convert milliseconds to seconds, minutes, hours, days, months, and years
        val seconds = difference / 1000
        val minutes = seconds / 60
        val hours = minutes / 60
        val days = hours / 24
        val months = days / 30
        val years = months / 12

        // Generate a human-readable string based on the time difference
        return when {
            years > 0 -> "$years year${if (years > 1) "s" else ""} ago"
            months > 0 -> "$months month${if (months > 1) "s" else ""} ago"
            days > 0 -> "$days day${if (days > 1) "s" else ""} ago"
            hours > 0 -> "$hours hour${if (hours > 1) "s" else ""} ago"
            minutes > 0 -> "$minutes minute${if (minutes > 1) "s" else ""} ago"
            else -> "$seconds second${if (seconds > 1) "s" else ""} ago"
        }
    }
}