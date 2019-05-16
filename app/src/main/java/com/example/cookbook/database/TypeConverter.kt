package com.example.cookbook.database

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlin.collections.ArrayList

class TypeConverter {
    var gson = Gson()

    @TypeConverter
    fun toList(data: String): ArrayList<String> {
        if(data.isEmpty())
            return arrayListOf()

        var type = object: TypeToken<List<String>>() {}.type

        return gson.fromJson(data, type)
    }

    @TypeConverter
    fun toString(data: ArrayList<String>): String {
        return gson.toJson(data)
    }
}