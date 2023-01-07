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
import ir.ha.dummy.utility.extentions.showToast
import java.util.*

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
            val post = viewModel.getPostOnLiveData(postId)

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

                lifecycleScope.launchWhenCreated { viewModel.getSelectedPostComments(postId) }

                binding.btnShareComment.setOnClickListener{

                    if (binding.etCommentInput.text.toString().isNotEmpty()){
                        val commentMessage = binding.etCommentInput.text.toString()
                        val commentReleasedTime = Calendar.getInstance().time
                        val commentReleasedDate = currentDate()
                        val user = post.user
                        val id = System.currentTimeMillis()+post.user.userId
                        val cm = Comment(id.toInt(),user,commentMessage,commentReleasedDate,commentReleasedTime.hours.toString() +":" + commentReleasedTime.minutes.toString())
                        adapter.addItem(cm)
                        post.postComments.add(cm)
                        RoomDB.database!!.postDao().updatePost(post)
                        binding.etCommentInput.setText("")
                        showToast(requireContext(),"Posted successfully :)")
                    }else { binding.etCommentInput.error = "comments inputs not found .." }

                }


                binding.tvShowMoreComment.setOnClickListener {
                   lifecycleScope.launchWhenCreated {
                       viewModel.getSelectedPostCommentsPaging(postId)
                   }
                }

            }

        }

    }

    private fun currentDate(): String {
        val c = Calendar.getInstance()
        val day = c[Calendar.DAY_OF_MONTH]
        val month = c[Calendar.MONTH]
        val year = c[Calendar.YEAR]
        val date = day.toString() + "/" + (month + 1) + "/" + year
        return date
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

        viewModel.isDone.observe(viewLifecycleOwner){
            binding.progressBar.visibility = if (it) View.GONE else View.VISIBLE
            binding.tvShowMoreComment.visibility = if (!it) View.GONE else View.VISIBLE
        }
    }

    override fun onCommentClick(cm: Comment) {
        // comment clicked event
    }

    private fun updateLikeCount(post: Post) {
        RoomDB.database!!.postDao().updatePost(post)
        viewModel.updatePostOnLiveData(post)
        binding.tvLikesCount.text = post.likeCount()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        viewModel.canceledRequest()
        viewModel.clearCommentsLiveData()
    }


}