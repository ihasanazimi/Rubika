package com.example.rubika.ui.feacher.fragments.postList

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.rubika.model.Post
import com.example.rubika.utility.base.BaseViewModel

class PostVM : BaseViewModel(){

    private val _posts = MutableLiveData<ArrayList<Post>>()
    val posts : LiveData<ArrayList<Post>> = _posts


    fun getPost(){

    }
}