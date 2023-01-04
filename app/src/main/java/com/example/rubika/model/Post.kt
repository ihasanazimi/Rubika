package com.example.rubika.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Post(
    @PrimaryKey(autoGenerate = true)
    var id : Long = 0 ,
    var title: String,
    var cover: Int,
    var likeCount: Int,
    var comments: List<Comment>,
    var releasedDate: String,
    var releasedTime: String,
)