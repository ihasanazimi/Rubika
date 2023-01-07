package com.example.rubika.ui.feacher.post.details

import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.example.rubika.R
import com.example.rubika.databinding.FragmentPostDetailsBinding
import com.example.rubika.model.Comment
import com.example.rubika.model.Post
import com.example.rubika.repository.datasource.db.RoomDB
import com.example.rubika.ui.feacher.post.PostVM
import com.example.rubika.utility.base.BaseFragmentByVM
import com.example.rubika.utility.customViews.ToggleImageView

class PostDetailFragment : BaseFragmentByVM<FragmentPostDetailsBinding,PostVM>(), CommentAdapter.CommentEvents {
    override val layoutId: Int get() = R.layout.fragment_post_details
    override val viewModel: PostVM get() = ViewModelProvider(requireActivity())[PostVM::class.java]

    companion object{
        const val POST_ID = "POST_ID"
    }

    private lateinit var adapter : CommentAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        requireArguments().let {

            val postId = it.getInt(POST_ID)
            val post = viewModel.getPost(postId)

            if (post != null) {

                binding.data = post // set data to xml for dataBinding
                updateLikeCountUi(post)
                loadImages(post)
                setupRecyclerView()

                // like - unLike
                binding.btnLike.addStateListener(object : ToggleImageView.OnStateChangedListener{

                    override fun onChecked() {
                        post.liked = true
                        post.likeCount ++
                        updateLikeCount(post)
                    }

                    override fun onUnchecked() {
                        post.liked = false
                        if (post.likeCount > 0) post.likeCount --
                        updateLikeCount(post)
                    }

                })

                if (viewModel.comments.value == null)
                lifecycleScope.launchWhenCreated { viewModel.getSelectedPostComments(postId) }

            }

        }

    }

    private fun setupRecyclerView() {
        adapter = CommentAdapter(this)
        binding.recyclerviewComments.adapter = adapter
    }

    private fun loadImages(post: Post) {
        Glide.with(binding.ivUserCover.context).load(post.postCoverUrl)
            .transition(DrawableTransitionOptions.withCrossFade())
            .placeholder(R.drawable.ic_baseline_downloading_24)
            .diskCacheStrategy(DiskCacheStrategy.ALL) // catch images
            .transform(RoundedCorners(10))  // corner radius
            .into(binding.ivPostCover)

        Glide.with(binding.ivUserCover.context).load(post.user.coverImage)
            .transition(DrawableTransitionOptions.withCrossFade())
            .placeholder(R.drawable.ic_baseline_downloading_24)
            .diskCacheStrategy(DiskCacheStrategy.ALL) // catch images
            .transform(RoundedCorners(10))  // corner radius
            .into(binding.ivUserCover)
    }

    private fun updateLikeCountUi(post: Post) {
        if (post.liked) binding.btnLike.setChecked()
        else binding.btnLike.setUnchecked()
    }

    override fun registerObservers() {
        super.registerObservers()
        viewModel.comments.observe(viewLifecycleOwner){
            adapter.setItemByDiffUtil(it)
        }
    }

    override fun onCommentClick(cm: Comment) {
        // comment clicked event
    }

    private fun updateLikeCount(post: Post) {
        RoomDB.database!!.postDao().updatePost(post)
        viewModel.updatePost(post)
        binding.tvLikesCount.text = post.likeCount()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        viewModel.canceledRequest()
        viewModel.clearCommentsLiveData()
    }


}