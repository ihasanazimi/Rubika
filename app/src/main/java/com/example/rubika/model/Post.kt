package com.example.rubika.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Post(
    @PrimaryKey
    @ColumnInfo(name = "id")
    var id : Int ,
    @ColumnInfo(name = "user")
    var user: User,
    @ColumnInfo
    var caption: String = "",
    @ColumnInfo(name = "postCoverUrl")
    var postCoverUrl: String ,
    @ColumnInfo (name = "likeCount")
    var likeCount: Int = -1,
    @ColumnInfo (name = "liked")
    var liked: Boolean,
    @ColumnInfo(name = "postComments")
    var postComments: ArrayList<Comment>,
    @ColumnInfo(name = "releasedDate")
    var releasedDate: String = "",
    @ColumnInfo(name = "releasedTime")
    var releasedTime: String = ""
) : java.io.Serializable {

    fun likeCount() = "$likeCount likes"
    fun commentsCount() = "${postComments.size} comments"
    fun dateAndTime() = "$releasedDate\n$releasedTime"


}