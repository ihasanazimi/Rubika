package com.example.rubika.ui.feacher.post.list

import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.RecyclerView
import com.example.rubika.R
import com.example.rubika.databinding.FragmentPostsBinding
import com.example.rubika.model.Post
import com.example.rubika.ui.feacher.post.PostVM
import com.example.rubika.ui.feacher.post.details.PostDetailFragment
import com.example.rubika.utility.EndlessRecyclerViewScrollListener
import com.example.rubika.utility.base.BaseFragmentByVM
import kotlinx.coroutines.DelicateCoroutinesApi

@DelicateCoroutinesApi
class PostListFragment : BaseFragmentByVM<FragmentPostsBinding,PostVM>(), PostAdapter.PostEvents {
    override val layoutId: Int get() = R.layout.fragment_posts
    override val viewModel: PostVM get() = ViewModelProvider(requireActivity())[PostVM::class.java]

    private lateinit var adapter : PostAdapter
    private lateinit var endlessListener: EndlessRecyclerViewScrollListener


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupCommentsRecyclerView()

        // initial request
        if (viewModel.posts.value == null) lifecycleScope.launchWhenCreated { viewModel.getFirstPageOfPost() }

        // paging
        endlessListener = object : EndlessRecyclerViewScrollListener(binding.recyclerView.layoutManager!!) {
                override fun onLoadMore(page: Int, totalItemsCount: Int, view: RecyclerView?) {
                    viewModel.postsPageNumber++
                    lifecycleScope.launchWhenCreated { viewModel.getPostPaging() }
                }
            }
        binding.recyclerView.addOnScrollListener(endlessListener)

    }




    override fun registerObservers() {
        super.registerObservers()

        viewModel.posts.observe(viewLifecycleOwner){
            adapter.setItemByDiffUtil(it)
        }


        viewModel.isDone.observe(viewLifecycleOwner){
            binding.progressBar.visibility = if (it) View.GONE else View.VISIBLE
        }

    }

    private fun setupCommentsRecyclerView() {
        adapter = PostAdapter(this)
        val decoration = DividerItemDecoration(requireContext(), RecyclerView.VERTICAL)
        binding.recyclerView.addItemDecoration(decoration)
        binding.recyclerView.adapter = adapter
    }

    override fun onPostClick(post: Post) {
        val bundle = Bundle()
        bundle.putInt(PostDetailFragment.POST_ID,post.id)
        findNavController().navigate(R.id.action_postListFragment_to_postDetailFragment, bundle)
    }

    override fun onUpdatePost(post: Post) {
        viewModel.updatePostOnLiveData(post)
        adapter.updatedItem(post)
    }


}