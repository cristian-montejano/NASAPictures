package com.example.nasapictures.repo

import com.example.nasapictures.api.RetrofitInstance
import com.example.nasapictures.model.Post
import retrofit2.Response

class Repository {

    suspend fun getPost(keyword: String): Response<Post> {
        return RetrofitInstance.api.searchPhotos(keyword)
    }
}