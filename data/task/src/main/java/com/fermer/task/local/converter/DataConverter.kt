package com.fermer.task.local.converter

import android.annotation.SuppressLint
import androidx.room.TypeConverter
import java.time.LocalDate

class  DataConverters {
    @SuppressLint("NewApi")
    @TypeConverter
    fun fromTimestamp(value: String?): LocalDate? {
        return value?.let { LocalDate.parse(it) }
    }

    @TypeConverter
    fun dateToTimestamp(date: LocalDate?): String? {
        return date?.toString()
    }
}