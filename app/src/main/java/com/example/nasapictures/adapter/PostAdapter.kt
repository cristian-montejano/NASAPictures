package com.example.nasapictures.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.nasapictures.R
import com.example.nasapictures.model.Items
import com.squareup.picasso.Picasso

class PostAdapter: RecyclerView.Adapter<PostAdapter.PostViewHolder>() {

    private var posts = mutableListOf<Items>()

    inner class PostViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.image_layout, parent, false)
        return PostViewHolder(view)
    }

    override fun getItemCount(): Int {
        return posts.size
    }

    override fun onBindViewHolder(holder: PostViewHolder, position: Int) {
        holder.itemView.findViewById<TextView>(R.id.tv_title).text = posts[position].data[0].title
        holder.itemView.findViewById<TextView>(R.id.tv_date).text = posts[position].data[0].date_created
        holder.itemView.findViewById<TextView>(R.id.tv_desc).text = posts[position].data[0].description

        Picasso.get().load(posts[position].links[0].href)
            .into(holder.itemView.findViewById<ImageView>(R.id.imageView))
    }

    fun setData(newList: ArrayList<Items>){
        posts = newList
        notifyDataSetChanged()
    }

}