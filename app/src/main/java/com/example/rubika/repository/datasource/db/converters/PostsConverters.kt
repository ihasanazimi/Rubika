package com.example.rubika.repository.datasource.db.converters

import androidx.room.TypeConverter
import com.example.rubika.model.Post
import com.google.gson.Gson

class PostsConverters {

    @TypeConverter
    fun fromPostToJson(post: Post): String {
        return Gson().toJson(post)
    }

    @TypeConverter
    fun fromJsonToPost(stringPost: String): Post {
        return Gson().fromJson(stringPost , Post::class.java)
    }

}