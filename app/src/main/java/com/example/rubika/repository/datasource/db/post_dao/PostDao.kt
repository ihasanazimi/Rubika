package com.example.rubika.repository.datasource.db.post_dao

import androidx.room.*
import com.example.rubika.model.Comment
import com.example.rubika.model.Post

@Dao
interface PostDao {

    @Insert(onConflict =  OnConflictStrategy.REPLACE)
    fun insertPost(PostModel: Post) : Long

    @Delete
    fun deletePost(PostModel: Post) : Int

    @Update
    fun updatePost(PostModel  : Post)

    @Query("select * from Post limit 10;")
    fun allPosts() : List<Post>

    @Query("select * from Post limit 10 OFFSET :page*10;")
    fun allPosts(page : Int) : List<Post>

    @Query("select * from Post where id = :postId;")
    fun getPost( postId : Int) : List<Post>


}