package com.example.rubika.repository.datasource.db.converters

import androidx.room.TypeConverter
import com.example.rubika.model.Comment
import com.google.gson.Gson

class CommentConverters {

    @TypeConverter
    fun fromCommentToJson(comment: Comment): String {
        return Gson().toJson(comment)
    }

    @TypeConverter
    fun fromJsonToComment(stringComment: String): Comment {
        return Gson().fromJson(stringComment , Comment::class.java)
    }

}