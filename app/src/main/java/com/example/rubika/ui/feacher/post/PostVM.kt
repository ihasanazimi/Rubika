package com.example.rubika.ui.feacher.post

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.rubika.repository.datasource.db.RoomDB
import com.example.rubika.model.Comment
import com.example.rubika.model.Post
import com.example.rubika.utility.base.BaseViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class PostVM : BaseViewModel() {

    var postsPageNumber = 0
    var commentPageNumber = 0

    private val _posts = MutableLiveData<ArrayList<Post>>()
    val posts: LiveData<ArrayList<Post>> = _posts

    private val _comments= MutableLiveData<ArrayList<Comment>>()
    val comments: LiveData<ArrayList<Comment>> = _comments

    val isDone = MutableLiveData<Boolean>(true)


    suspend fun getPost() {
        isDone.value = false
        delay(1200)
        viewModelScope.launch {
            val posts = RoomDB.database!!.postDao().allPosts(postsPageNumber) as ArrayList<Post>
            if (posts.isNotEmpty()) {
                val temps = _posts.value?.toMutableList() ?: arrayListOf()
                temps.addAll(posts)
                _posts.value = temps as ArrayList<Post> ?: arrayListOf()
            } else postsPageNumber--
            isDone.value = true
        }
    }


    suspend fun getFirstPageOfPost() {
        isDone.value = false
        delay(1200)
        viewModelScope.launch {
            val posts = RoomDB.database!!.postDao().allPosts()
            _posts.value = posts as ArrayList<Post> ?: arrayListOf()
        }
        isDone.value = true
    }


    suspend fun getSelectedPostComments(postId : Int) {
        isDone.value = false
        delay(1200)
        viewModelScope.launch {
            val posts = RoomDB.database!!.postDao().getPost(postId)
            val targetPost = posts.find { it.id == postId }
            _comments.value = targetPost?.postComments?: arrayListOf()
        }
        isDone.value = true
    }


    fun updatePost(updatedPost: Post){
        val temps = _posts.value?.toMutableList()
        val targetPost = temps?.find { it.id == updatedPost.id }
        val index = temps?.indexOf(targetPost)
        if (index != null) temps.set(index,updatedPost)
        _posts.value = temps as ArrayList<Post>?
        isDone.value = true
    }

    fun getPost(postId: Int) : Post{
        val temps = _posts.value?.toMutableList()
        val targetPost = temps?.find { it.id == postId }
        return targetPost!!
    }


    // for clear data after change state
    fun clearCommentsLiveData(){
        val data = _comments.value?.apply {
            this.clear() } ?: arrayListOf()
        _comments.postValue(data)
    }

    fun canceledRequest(){
        isDone.value = true
    }

}
