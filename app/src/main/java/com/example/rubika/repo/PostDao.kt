package com.example.rubika.repo

import androidx.room.*
import com.example.rubika.model.Post

@Dao
interface PostDao {

    @Insert(onConflict =  OnConflictStrategy.REPLACE)
    fun insertPost(PostModel: Post) : Long

    @Delete
    fun deletePost(PostModel: Post) : Int

    @Update
    fun updatePost(PostModel  : Post)

    @Query("select * from Post")
    fun allPosts() : List<Post>

}