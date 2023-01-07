package com.example.rubika.ui.feacher.post.list

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.example.rubika.ApplicationLoader
import com.example.rubika.R
import com.example.rubika.databinding.ItemPostBinding
import com.example.rubika.repository.datasource.db.RoomDB
import com.example.rubika.model.Post
import com.example.rubika.utility.customViews.ToggleImageView
import ir.ha.dummy.utility.TDiffUtilCallback

class PostAdapter(val callBack : PostEvents) : RecyclerView.Adapter<PostAdapter.VH>() {

    private var items = arrayListOf<Post>()

    interface PostEvents{
        fun onPostClick(post : Post)
        fun onUpdatePost(post : Post)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH {
        return VH(ItemPostBinding.inflate(LayoutInflater.from(parent.context),parent,false))
    }

    override fun onBindViewHolder(holder: VH, @SuppressLint("RecyclerView") position: Int) {

        holder.bind(items[position])

        holder.binding.ivPostCover.setOnClickListener{
            callBack.onPostClick(items[position])
        }

        holder.binding.commentsContainer.setOnClickListener{
            callBack.onPostClick(items[position])
        }


        holder.binding.btnLike.addStateListener(object : ToggleImageView.OnStateChangedListener{
            override fun onChecked() {
                items[position].likeCount ++
                items[position].liked = true
                callBack.onUpdatePost(items[position])
            }
            override fun onUnchecked() {
                if (items[position].likeCount > 0) items[position].likeCount --
                items[position].liked = false
                callBack.onUpdatePost(items[position])
            }
        })
    }

    override fun getItemCount(): Int {
        return items.size
    }

    fun removeItem(model: Post){
        val index = items.indexOf(model)
        items.removeAt(index)
        notifyItemRemoved(index)
        ApplicationLoader.applicationHandler.postDelayed({notifyDataSetChanged()},200)
    }

    fun addItem(model: Post){
        items.add(model)
        notifyItemInserted(itemCount)
        ApplicationLoader.applicationHandler.postDelayed({notifyDataSetChanged()},200)
    }

    fun setItemByDiffUtil(list: ArrayList<Post>){
        val diffCallback = TDiffUtilCallback<Post>(this.items, list)
        val diffResult = DiffUtil.calculateDiff(diffCallback)
        this.items.clear()
        this.items.addAll(list)
        diffResult.dispatchUpdatesTo(this)
    }

    fun addItems(items: ArrayList<Post>) {
        this.items.addAll(items)
        val startPos = this.items.size - items.size
        notifyItemRangeInserted(startPos, items.size)
    }

    fun updatedItem(updatedPost: Post) {
        val targetPostBeforeChanged = items.find { it.id == updatedPost.id }
        val index = items.indexOf(targetPostBeforeChanged)
        this.items.set(index,updatedPost)
        notifyDataSetChanged()
    }


    fun clearList(){
        items.clear()
        ApplicationLoader.applicationHandler.postDelayed({notifyDataSetChanged()},200)
    }



    inner class VH(val binding: ItemPostBinding) : RecyclerView.ViewHolder(binding.root){

        fun bind(post : Post){
            binding.data = post

            if (post.liked)binding.btnLike.setChecked()
            else binding.btnLike.setUnchecked()
            RoomDB.database!!.postDao().updatePost(post)

            Glide.with(binding.ivUserCover.context)
                .load(post.postCoverUrl)
                .transition(DrawableTransitionOptions.withCrossFade())
                .placeholder(R.drawable.ic_baseline_downloading_24)
                .into(binding.ivPostCover)

            Glide.with(binding.ivUserCover.context)
                .load(post.user.coverImage)
                .transition(DrawableTransitionOptions.withCrossFade())
                .placeholder(R.drawable.ic_baseline_downloading_24)
                .into(binding.ivUserCover)
        }
    }

}