package com.fermer.task.local.converter

import androidx.room.TypeConverter
import com.fermer.model.Priority

class PriorityConverter {
    @TypeConverter
    fun fromPriority(priority: Priority): String = priority.name

    @TypeConverter
    fun toPriority(value: String): Priority = Priority.valueOf(value)
}