package com.example.rubika.repository.datasource.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.rubika.repository.datasource.db.post_dao.PostDao
import com.example.rubika.model.Comment
import com.example.rubika.model.Post
import com.example.rubika.model.User
import com.example.rubika.repository.datasource.db.converters.*

@Database(entities = [Post::class,User::class,Comment::class ], version = 4)

@TypeConverters(
    PostsConverters::class,
    UserConverter::class,
    CommentListConverter::class,
    IntegerConverter::class
)

abstract class RoomDB : RoomDatabase() {

    // Dao
    abstract fun postDao(): PostDao

    companion object {
        @Volatile //access just one there on main thread!
        var database: RoomDB? = null
        // singleTon design pattern
        fun getDataBase(context: Context): RoomDB {
            val tempInstance = database
            if (database != null) return tempInstance as RoomDB
            //synchronized  -->  means -->  access just one there on main thread!
            synchronized(this) {
                val instance = Room.databaseBuilder(context, RoomDB::class.java, "database")
                    .fallbackToDestructiveMigration().allowMainThreadQueries().build()
                database = instance
                return instance
            }
        }
    }
}