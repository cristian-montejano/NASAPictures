package com.example.nasapictures

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.example.nasapictures.adapter.PostAdapter
import com.example.nasapictures.model.Items
import com.example.nasapictures.repo.Repository

class MainActivity : AppCompatActivity() {

    private val tags = listOf("stars", "mars", "shuttle", "planet", "comet")
    private lateinit var keyword: String
    private lateinit var viewModel: MainViewModel
    private lateinit var refreshLayout: SwipeRefreshLayout
    private val adapter by lazy { PostAdapter() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        refreshLayout = findViewById(R.id.refreshLayout)

        setRecyclerView()

        val repository = Repository()
        val viewModelFactory = MainViewModelFactory(repository)
        viewModel = ViewModelProvider(this, viewModelFactory).get(MainViewModel::class.java)

        getSearchTerm()
        getPosts()

        refreshLayout.setOnRefreshListener {
            getSearchTerm()
            getPosts()
        }
    }

    private fun getPosts() {
        viewModel.getPost(keyword)
        viewModel.myResponse.observe(this, Observer { response ->
            if (response.isSuccessful) {
                refreshLayout.isRefreshing = false
                response.body()?.collection?.items?.let { allPosts ->
                    val imagePosts = allPosts.filter { it.data[0].media_type == "image"}
                    adapter.setData(imagePosts as ArrayList<Items>) }
            }
            else {
                refreshLayout.isRefreshing = false
                Toast.makeText(this, response.code(), Toast.LENGTH_SHORT).show()
            }
        })
    }

    private fun getSearchTerm() {
        keyword = tags.random()
    }

    private fun setRecyclerView() {
        val recyclerView = findViewById<RecyclerView>(R.id.recyclerView)
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(this)
    }
}