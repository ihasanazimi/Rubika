package com.example.rubika.ui.feacher.post.details

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.example.rubika.ApplicationLoader
import com.example.rubika.R
import com.example.rubika.databinding.ItemCommentBinding
import com.example.rubika.model.Comment
import ir.ha.dummy.utility.TDiffUtilCallback

class CommentAdapter(val callBack : CommentEvents) : RecyclerView.Adapter<CommentAdapter.VH>() {

    private var items = arrayListOf<Comment>()

    interface CommentEvents{
        fun onCommentClick(cm : Comment)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH {
        return VH(ItemCommentBinding.inflate(LayoutInflater.from(parent.context),parent,false))
    }

    override fun onBindViewHolder(holder: VH, position: Int) {
        holder.bind(items[position])
        holder.itemView.setOnClickListener{
            callBack.onCommentClick(items[position])
        }
    }

    override fun getItemCount(): Int {
        return items.size
    }

    fun removeItem(model: Comment){
        val index = items.indexOf(model)
        items.removeAt(index)
        notifyItemRemoved(index)
        ApplicationLoader.applicationHandler.postDelayed({notifyDataSetChanged()},200)
    }

    fun addItem(model: Comment){
        items.add(model)
        notifyItemInserted(itemCount)
        ApplicationLoader.applicationHandler.postDelayed({notifyDataSetChanged()},200)
    }

    fun setItemByDiffUtil(list: ArrayList<Comment>){
        val diffCallback = TDiffUtilCallback<Comment>(this.items, list)
        val diffResult = DiffUtil.calculateDiff(diffCallback)
        this.items.clear()
        this.items.addAll(list)
        diffResult.dispatchUpdatesTo(this)
    }

    fun addItems(items: ArrayList<Comment>) {
        this.items.addAll(items)
        val startPos = this.items.size - items.size
        notifyItemRangeInserted(startPos, items.size)
    }


    fun clearList(){
        items.clear()
        ApplicationLoader.applicationHandler.postDelayed({notifyDataSetChanged()},200)
    }



    inner class VH(val binding: ItemCommentBinding) : RecyclerView.ViewHolder(binding.root){

        fun bind(cm : Comment){
            binding.tvUserName.text = cm.user.userName
            binding.tvCommentMessage.text = cm.message
            binding.tvPostReleasedDateTime.text = cm.releasedDate +" | " + cm.releasedTime

            Glide.with(binding.ivUserCover.context).load(cm.user.coverImage)
                .transition(DrawableTransitionOptions.withCrossFade())
                .placeholder(R.drawable.ic_baseline_downloading_24)
                .diskCacheStrategy(DiskCacheStrategy.ALL) // catch images
                .transform(RoundedCorners(10))  // corner radius
                .into(binding.ivUserCover)
        }
    }

}