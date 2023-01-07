package com.example.rubika.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class User(
    @PrimaryKey
    @ColumnInfo(name = "userId")
    val userId: Int ,
    @ColumnInfo(name = "nikeName")
    val nikeName: String  = "",
    @ColumnInfo(name = "userName")
    val userName: String = "",
    @ColumnInfo(name = "coverImage")
    val coverImage: String = ""
)
