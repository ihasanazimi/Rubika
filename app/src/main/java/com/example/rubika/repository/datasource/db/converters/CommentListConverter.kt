package com.example.rubika.repository.datasource.db.converters

import android.util.Log
import androidx.room.TypeConverter
import com.example.rubika.model.Comment
import com.google.gson.Gson
import com.google.gson.reflect.*

class CommentListConverter {


    @TypeConverter
    fun fromCommentListToJson(list: List<Comment>?): String? {
        return Gson().toJson(list)
    }

    @TypeConverter
    fun fromJsonToCommentList(jsonString: String): ArrayList<Comment> {
        return Gson().fromJson(jsonString , Array<Comment>::class.java).toCollection(ArrayList<Comment>())
    }

    @TypeConverter
    fun fromCommentToJson(comment: Comment): String {
        return Gson().toJson(comment)
    }

    @TypeConverter
    fun fromJsonToComment(stringComment: String): Comment {
        return Gson().fromJson(stringComment , Comment::class.java)
    }


}