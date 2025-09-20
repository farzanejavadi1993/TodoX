package com.fermer.common.util

import android.annotation.SuppressLint
import java.time.LocalDate
import java.time.format.DateTimeFormatter

@SuppressLint("NewApi")
fun LocalDate.toFormattedDate(): String {
    return this.format(DateTimeFormatter.ofPattern("dd MMM yyyy"))
}

@SuppressLint("NewApi")
fun LocalDate.isToday(): Boolean {
    return this == LocalDate.now()
}