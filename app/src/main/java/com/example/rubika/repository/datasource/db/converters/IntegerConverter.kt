package com.example.rubika.repository.datasource.db.converters

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.util.ArrayList

class IntegerConverter {

    @TypeConverter
    fun fromIntListToJson(list: List<Int>?): String? {
        return Gson().toJson(list)
    }

    @TypeConverter
    fun fromStringToIntegersList(jsonString: String): ArrayList<Int> {
        val listType = object : TypeToken<ArrayList<Int?>?>() {}.type
        return Gson().fromJson(jsonString, listType)
    }

}