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

class SharedPostVM : BaseViewModel() {

    var postsPageNumber = 0

    private val _posts = MutableLiveData<ArrayList<Post>>()
    val posts: LiveData<ArrayList<Post>> = _posts

    private val _comments= MutableLiveData<ArrayList<Comment>>()
    val comments: LiveData<ArrayList<Comment>> = _comments

    val isDone = MutableLiveData<Boolean>(true)
    val hideShowMoreBtn = MutableLiveData<Boolean>(true)



    suspend fun getPostPaging() {
        isDone.value = false
        delay(1200)
        viewModelScope.launch {
            val posts = RoomDB.database!!.postDao().allPostsPaging(postsPageNumber) as ArrayList<Post>
            if (posts.isNotEmpty()) {
                val temps = _posts.value?.toMutableList() ?: arrayListOf()
                temps.addAll(posts)
                _posts.value = temps as ArrayList<Post> ?: arrayListOf()
            } else postsPageNumber--
        }
        isDone.value = true
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
            if (targetPost != null && targetPost.postComments.isNotEmpty()) {
                val temps = arrayListOf<Comment>()
                (0..9).forEach {
                    if (it < targetPost.postComments.size) temps.add(targetPost.postComments[it])
                    else return@forEach
                }
                _comments.value = temps
                hideShowMoreBtn.value = false
                if (temps.size == targetPost.postComments.size) hideShowMoreBtn.value = true
            }
        }
        isDone.value = true
    }

    suspend fun getSelectedPostCommentsPaging(postId : Int) {
        isDone.value = false
        delay(1200)
        viewModelScope.launch {
            val posts = RoomDB.database!!.postDao().getPost(postId)
            val targetPost = posts.find { it.id == postId }
            if (targetPost != null && targetPost.postComments.isNotEmpty()) {
                val allComments = targetPost.postComments
                val temps = _comments.value?.toMutableList()
                val start = temps?.size!!
                val nextPage = start + 10
                (start..nextPage).forEach{
                    if (it < allComments.size){
                        if (allComments[it] != null) temps.add(allComments[it])
                        else return@forEach
                    }
                }
                _comments.value = temps as ArrayList<Comment>?
                hideShowMoreBtn.value = false
                if (temps.size == allComments.size) hideShowMoreBtn.value = true
            }
        }
        isDone.value = true
    }






    fun updatePostOnLiveData(updatedPost: Post){
        val temps = _posts.value?.toMutableList()
        val targetPost = temps?.find { it.id == updatedPost.id }
        val index = temps?.indexOf(targetPost)
        if (index != null) temps.set(index,updatedPost)
        _posts.value = temps as ArrayList<Post>?
        isDone.value = true
    }

    fun getPostOnLiveData(postId: Int) : Post{
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

    fun resetStates(){
        isDone.value = true
        hideShowMoreBtn.value = true
    }

}
