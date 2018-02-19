package com.example.richard.rnoteauth.helpers

import java.text.SimpleDateFormat
import java.util.*

class DateHelper {
    companion object {
        fun getCurrentDate(): String {
            return SimpleDateFormat("dd-MM-yyyy 'T' HH:mm:ss").format(Calendar.getInstance().time)
        }
    }
}