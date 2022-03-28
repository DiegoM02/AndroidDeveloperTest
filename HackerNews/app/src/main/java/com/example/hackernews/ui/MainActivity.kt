package com.example.hackernews.ui

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.example.hackernews.R
import com.example.hackernews.data.models.Hit
import com.example.hackernews.ui.list_news.ListHackerNewsAdapter
import com.example.hackernews.ui.list_news.ListHackerNewsViewModel
import com.example.hackernews.util.SwipeToDeleteCallback
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private val listNewsViewModel: ListHackerNewsViewModel by viewModels()
    private lateinit var recyclerViewHackerNews: RecyclerView
    private lateinit var hackerNewsAdapter: ListHackerNewsAdapter
    private lateinit var swipeRefreshLayout: SwipeRefreshLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setupRecyclerView()
        setupSwipeRefresh()
        setDataHackerNews()

    }

    private fun setupRecyclerView() {
        hackerNewsAdapter = ListHackerNewsAdapter()
        recyclerViewHackerNews = findViewById(R.id.recycler_view_hackerNews)
        recyclerViewHackerNews.adapter = hackerNewsAdapter
        recyclerViewHackerNews.setHasFixedSize(true)
        recyclerViewHackerNews.layoutManager = LinearLayoutManager(this)
        hackerNewsAdapter.setOnItemClickListener { handleClickListener(it) }
        setupSwipeDeleteCallback()

    }

    private fun setupSwipeRefresh(){
        swipeRefreshLayout = findViewById(R.id.swipeRefresh)
        swipeRefreshLayout.setOnRefreshListener {
            lifecycleScope.launch {
                withContext(Dispatchers.Main) { swipeRefreshLayout.isRefreshing = true}
                listNewsViewModel.getAllNews().collect{
                    withContext(Dispatchers.Main) { swipeRefreshLayout.isRefreshing = false}
                    hackerNewsAdapter.submitData(it)
                    hackerNewsAdapter.notifyDataSetChanged()
                }
            }
        }
    }


    private fun setupSwipeDeleteCallback(){
        val swipeHandler = object : SwipeToDeleteCallback(this) {
            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                val adapter = recyclerViewHackerNews.adapter as ListHackerNewsAdapter
                adapter.notifyItemRemoved(viewHolder.adapterPosition)
            }
        }
        val itemTouchHelper = ItemTouchHelper(swipeHandler)
        itemTouchHelper.attachToRecyclerView(recyclerViewHackerNews)
    }

    private fun handleClickListener(hit: Hit) {
        goToDetailHakcerNews(hit)
    }




    private fun setDataHackerNews() {
        lifecycleScope.launchWhenCreated {
           withContext(Dispatchers.Main) { swipeRefreshLayout.isRefreshing = true}
            listNewsViewModel.hackerNews?.collectLatest { pagedData ->

                withContext(Dispatchers.Main) {swipeRefreshLayout.isRefreshing = false}
                hackerNewsAdapter.submitData(pagedData)

            }
        }

    }

    private fun goToDetailHakcerNews(hackerNew: Hit){
        val intent = Intent(this, DetailHackerNewsActivity::class.java)
        intent.putExtra("url", hackerNew.url)
        startActivity(intent)
    }


}