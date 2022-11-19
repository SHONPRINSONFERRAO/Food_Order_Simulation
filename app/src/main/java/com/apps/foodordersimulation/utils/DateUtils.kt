package com.apps.foodordersimulation.utils

import java.text.SimpleDateFormat
import java.util.*

object DateUtils {

    fun getDateTime(s: Long): String? {
        try {
            return SimpleDateFormat("MM/dd/yyyy, HH:mm").format(Date(s))
        } catch (e: Exception) {
            return ""
        }
    }
}