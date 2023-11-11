package com.example.bakemate

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

class FeedAdapter(
    private val feedImageList: MutableList<String>,
    private val titleList: MutableList<String>
) : RecyclerView.Adapter<FeedAdapter.ViewHolder>() {
    class ViewHolder(view: View) : RecyclerView.ViewHolder(view){
        val feedImage : ImageView
        val feedImageTitle : TextView

        init {
            feedImage = view.findViewById(R.id.feed_image)
            feedImageTitle = view.findViewById(R.id.title_text)
        }
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.feed_image, parent, false)

        return  ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        Glide.with(holder.itemView)
            .load(feedImageList[position])
            .centerCrop()
            .into(holder.feedImage)

        holder.feedImageTitle.text = titleList[position]
    }

    override fun getItemCount() = feedImageList.size

}