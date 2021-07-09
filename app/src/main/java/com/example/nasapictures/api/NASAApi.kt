package com.example.nasapictures.api

import com.example.nasapictures.model.Post
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface NASAApi {

    @GET("search")
    suspend fun searchPhotos(@Query("q") keyword: String) : Response<Post>

}