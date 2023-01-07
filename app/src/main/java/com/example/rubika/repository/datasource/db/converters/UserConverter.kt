package com.example.rubika.repository.datasource.db.converters

import androidx.room.TypeConverter
import com.example.rubika.model.User
import com.google.gson.Gson

class UserConverter {

    @TypeConverter
    fun fromUserToJson(user: User): String {
        return Gson().toJson(user)
    }

    @TypeConverter
    fun fromJsonToUser(stringUser: String): User {
        return Gson().fromJson(stringUser , User::class.java)
    }

}