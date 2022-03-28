package com.example.hackernews.ui.list_news

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.hackernews.R
import com.example.hackernews.data.models.Hit
import com.example.hackernews.util.DateUtil

class ListHackerNewsAdapter: PagingDataAdapter<Hit, ListHackerNewsAdapter.HackerNewsViewHolder>(HitComprator) {

    inner class HackerNewsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        private lateinit var storyTitle: TextView
        private lateinit var  author: TextView
        private  lateinit var  createdAt: TextView

        fun bind(hackerNews: Hit) {
            storyTitle = itemView.findViewById(R.id.story_tittle)
            author = itemView.findViewById(R.id.author)
            createdAt = itemView.findViewById(R.id.date_created_at)

            storyTitle.text = hackerNews.title
            author.text = hackerNews.author
            createdAt.text = DateUtil.getTimeElapsed(hackerNews.created_at!!)

            itemView.setOnClickListener{
                onItemCLickListener?.let {
                    it(hackerNews)
                }
            }
        }
    }


   object HitComprator : DiffUtil.ItemCallback<Hit>() {
        override fun areItemsTheSame(oldItem: Hit, newItem: Hit): Boolean {
            return oldItem.story_id == newItem.story_id
        }

        override fun areContentsTheSame(oldItem: Hit, newItem: Hit): Boolean {
            return oldItem == newItem
        }

    }

    //val differ = AsyncListDiffer(this, differCallBack)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HackerNewsViewHolder {
        return HackerNewsViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.item_hacker_news,
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: HackerNewsViewHolder, position: Int) {
        val hackerNewItem = getItem(position)
        if (hackerNewItem != null) {
            holder.bind(hackerNewItem)
        }
    }

    private var onItemCLickListener: ((Hit) -> Unit)? = null


    fun setOnItemClickListener(listener: (Hit) -> Unit){
        onItemCLickListener = listener
    }

}