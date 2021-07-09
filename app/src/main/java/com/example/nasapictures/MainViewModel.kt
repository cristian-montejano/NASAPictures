package com.example.nasapictures

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.nasapictures.model.Post
import com.example.nasapictures.repo.Repository
import kotlinx.coroutines.launch
import retrofit2.Response

class MainViewModel(private val repository: Repository): ViewModel() {

    val myResponse: MutableLiveData<Response<Post>> = MutableLiveData()

    fun getPost(keyword: String) {
        viewModelScope.launch {
            val response = repository.getPost(keyword)
            myResponse.value = response
        }
    }
}