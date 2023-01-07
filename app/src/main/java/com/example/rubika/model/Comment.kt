package com.example.rubika.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Comment(
    @PrimaryKey
    @ColumnInfo(name = "commentId")
    val commentId : Int ,
    @ColumnInfo(name = "user")
    var user: User ,
    @ColumnInfo(name = "message")
    var message: String  = "",
    @ColumnInfo(name = "releasedDate")
    var releasedDate: String = "",
    @ColumnInfo(name = "releasedTime")
    var releasedTime: String = ""
)
