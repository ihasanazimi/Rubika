package com.example.rubika.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Comment(
    @PrimaryKey(autoGenerate = true)
    var id: Long = 0,
    var ownerName: String,
    var releasedDate: String,
    var releasedTime: String,
    var message: String
)
