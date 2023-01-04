package com.example.rubika.ui

import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModelProvider
import com.example.rubika.R
import com.example.rubika.databinding.FragmentPostsBinding
import com.example.rubika.ui.feacher.fragments.postList.PostVM
import com.example.rubika.utility.base.BaseFragmentByVM

class PostListFragment : BaseFragmentByVM<FragmentPostsBinding , PostVM>() {
    override val layoutId: Int get() = R.layout.fragment_posts
    override val viewModel: PostVM get() = ViewModelProvider(requireActivity())[PostVM::class.java]

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }
}